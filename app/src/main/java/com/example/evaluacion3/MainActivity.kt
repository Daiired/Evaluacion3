package com.example.evaluacion3

import android.Manifest
import android.content.Context
import android.graphics.BitmapFactory
import android.location.Location
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.camera.core.CameraSelector
import androidx.camera.core.ImageCapture
import androidx.camera.core.ImageCaptureException
import androidx.camera.view.CameraController
import androidx.camera.view.LifecycleCameraController
import androidx.camera.view.PreviewView
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.graphics.painter.BitmapPainter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.Priority
import org.osmdroid.config.Configuration
import org.osmdroid.tileprovider.tilesource.TileSourceFactory
import org.osmdroid.util.GeoPoint
import org.osmdroid.views.MapView
import org.osmdroid.views.overlay.Marker
import java.io.File
import java.time.LocalDateTime


enum class Pantalla{
    FORM,
    FOTO
}

class CameraViewModel: ViewModel(){
    val pantalla = mutableStateOf(Pantalla.FORM)

    var onPermisoCamOk : ()->Unit = {}
    var onPermisoUbiOk : ()->Unit = {}

    var lanzadorPermisos:ActivityResultLauncher<Array<String>>? = null

    fun cambPantallaFoto(){ pantalla.value = Pantalla.FOTO}
    fun cambPantallaForm(){ pantalla.value = Pantalla.FORM}
}

class FormRecepecionViewModel: ViewModel(){
    val lugar = mutableStateOf("")
    val latitud = mutableStateOf(0.0)
    val longitud = mutableStateOf(0.0)
    val foto = mutableStateOf<Uri?>(null)
}


class MainActivity : ComponentActivity() {
    val cameraVM:CameraViewModel by viewModels()

    lateinit var  cameraController: LifecycleCameraController

    val lanzadorPermisos = registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()){
        when {
            (it[Manifest.permission.ACCESS_FINE_LOCATION] ?: false) or (it[Manifest.permission.ACCESS_COARSE_LOCATION] ?: false)-> {
                Log.v("callback RequestMultiplePermissions", "permiso camara granted")
                cameraVM.onPermisoUbiOk()
            }
            (it[Manifest.permission.CAMERA]  ?: false)->{
                Log.v("callback RequestMultiplePermissions", "permiso camara granted")
                cameraVM.onPermisoCamOk()
            }
            else->{

            }


        }
    }

    private fun setupCamara(){
        cameraController = LifecycleCameraController(this)
        cameraController.bindToLifecycle(this)
        cameraController.cameraSelector = CameraSelector.DEFAULT_BACK_CAMERA
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        cameraVM.lanzadorPermisos = lanzadorPermisos
        setupCamara()
        setContent {
            AppUI(cameraController)
        }
    }
}

@Composable
fun AppUI(cameraController: CameraController){
    val contexto = LocalContext.current

    val formRecepecionVM:FormRecepecionViewModel = viewModel()
    val cameraViewModel : CameraViewModel = viewModel()

    when(cameraViewModel.pantalla.value) {
        Pantalla.FORM -> {
            PantallaFormUI(
                formRecepecionVM,
                tomarFotoOnClick = {
                    cameraViewModel.cambPantallaFoto()
                    cameraViewModel.lanzadorPermisos?.launch(arrayOf(Manifest.permission.CAMERA))
                },
                actualizarUbicacionOnClick = {
                    cameraViewModel.onPermisoUbiOk = {
                        getUbicacion(contexto) {
                            formRecepecionVM.latitud.value = it.latitude
                            formRecepecionVM.longitud.value = it.longitude
                        }
                    }
                    cameraViewModel.lanzadorPermisos?.launch(arrayOf(Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION))
                }
            )
        }
        Pantalla.FOTO -> {
            PantallaFotoUI(formRecepecionVM, cameraViewModel, cameraController)
        }
        else -> {
            Log.v("AppUI()", "when else, no debería entrar aquí")
        }
    }

}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PantallaFormUI(
    formRecepecionVM: FormRecepecionViewModel,
    tomarFotoOnClick: ()->Unit = {},
    actualizarUbicacionOnClick: ()-> Unit ={}
){
    val contexto = LocalContext.current
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        TextField(
            label = { Text("Nombre Lugar")},
            value = formRecepecionVM.lugar.value,
            onValueChange = {formRecepecionVM.lugar.value = it},
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 10.dp)
        )

        Text("Fotos lugar al que fue:")
        Button(onClick = { tomarFotoOnClick }) {
            Text("Tomar Foto")
        }

        formRecepecionVM.foto.value?.also{
            Box(Modifier.size(200.dp, 100.dp)){
                Image(painter = BitmapPainter(uri2imageBitMap(it, contexto)), contentDescription = "Imagen lugar visitado: ${formRecepecionVM.lugar.value}")
            }
        }

        Text("La ubicacion del lugar es: latitud: ${formRecepecionVM.latitud.value} y longitud: ${formRecepecionVM.longitud.value}")
        Button(onClick = { actualizarUbicacionOnClick}) {
            Text("Actualizar Ubicacion")
        }

        Spacer(Modifier.height(100.dp))
        MapaOsmUI(formRecepecionVM.latitud.value, formRecepecionVM.longitud.value)
    }


}

@Composable
fun MapaOsmUI(
    latitud:Double,
    longitud:Double
){
    val contexto = LocalContext.current

    AndroidView(
        factory = {
            MapView(it).also {
                it.setTileSource(TileSourceFactory.MAPNIK)
                Configuration.getInstance().userAgentValue = contexto.packageName
            }
        },update={
            it.overlays.removeIf{ true}
            it.invalidate()

            it.controller.setZoom(18.0)

            val geoPoint = GeoPoint(latitud,longitud)
            it.controller.animateTo(geoPoint)

            val marcador = Marker(it)
            marcador.position = geoPoint
            marcador.setAnchor(Marker.ANCHOR_CENTER,Marker.ANCHOR_CENTER)
            it.overlays.add(marcador)
        }
    )
}

@Composable
fun PantallaFotoUI(
    formRecepecionVM:FormRecepecionViewModel,
    appViewModel:CameraViewModel,
    cameraController: CameraController
){
    val contexto = LocalContext.current

    AndroidView(
        factory = {
            PreviewView(it).apply {
                controller = cameraController
            }
        },
        modifier = Modifier.fillMaxSize()
    )

    Button(onClick = {
        tomarFoto(
            cameraController,
            crearArchivoImagenPrivado(contexto),
            contexto
        ) {
            formRecepecionVM.foto.value = it
            appViewModel.cambPantallaForm()
        }
    }) {
        Text("Tomar foto")
    }
}


fun generarNombreSegunFechaS():String = LocalDateTime.now().toString().replace(Regex("[T:.-]"), "").substring(0,14)

fun crearArchivoImagenPrivado(contexto: Context): File = File(contexto.getExternalFilesDir(Environment.DIRECTORY_PICTURES), "${generarNombreSegunFechaS()}.jpg")

fun tomarFoto(
    cameraController: CameraController,
    archivo: File,
    contexto: Context,
    imagenGuardadaOk: (uri: Uri) -> Unit
) {
    val outputFileOptions = ImageCapture.OutputFileOptions.Builder(archivo).build()

    cameraController.takePicture(
        outputFileOptions,
        ContextCompat.getMainExecutor(contexto),
        object : ImageCapture.OnImageSavedCallback {
            override fun onImageSaved(outputFileResults: ImageCapture.OutputFileResults) {
                outputFileResults.savedUri?.also {
                    Log.v("tomarFoto()::onImageSaved", "Foto guardada en ${it.toString()}")
                    imagenGuardadaOk(it)
                }
            }

            override fun onError(exception: ImageCaptureException) {
                Log.e("tomarFoto()", "Error: ${exception.message}")
            }
        }
    )
}

fun getUbicacion(contexto: Context, onUbicacionOk: (location: Location) -> Unit): Unit {
    try {
        val servicio = LocationServices.getFusedLocationProviderClient(contexto)
        val tarea = servicio.getCurrentLocation(Priority.PRIORITY_HIGH_ACCURACY, null)

        tarea.addOnSuccessListener { location ->
            onUbicacionOk(location)
        }
    } catch (e: SecurityException) {
        throw SinPermisoException(e.message ?: "No tiene permisos para conseguir la ubicación")
    }
}

class SinPermisoException(mensaje:String) : Exception(mensaje)

fun uri2imageBitMap(uri:Uri, contexto: Context) = BitmapFactory.decodeStream(contexto.contentResolver.openInputStream(uri)).asImageBitmap()
