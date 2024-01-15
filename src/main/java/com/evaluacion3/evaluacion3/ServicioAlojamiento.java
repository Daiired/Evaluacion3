/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.evaluacion3.evaluacion3;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.core.Response;

@Path("/alojamientos")
public class ServicioAlojamiento {
    
    private List<Usuario> getUsuarioFromDatebase(){
        List<Usuario> array = new ArrayList<>();
        array.add(new Usuario("JohnTrance", "123", "John", "Trance"));
        array.add(new Usuario("LindaStewart", "123", "Linda", "Stewart"));
        array.add(new Usuario("PhilCol", "123", "Phil", "Col"));
        array.add(new Usuario("StanSto", "123", "Stan", "Sto"));
        return array;
    }
    
    private List<Representante> getRepresentanteFromDatebase(){
        List<Representante> array = new ArrayList<>();
        array.add(new Representante("1-9", "Nombre genericos", "Apellidos genericos", "Telefono Generico"));
        array.add(new Representante("2-7", "Nombre genericos", "Apellidos genericos", "Telefono Generico"));
        array.add(new Representante("3-5", "Nombre genericos", "Apellidos genericos", "Telefono Generico"));
        array.add(new Representante("4-3", "Nombre genericos", "Apellidos genericos", "Telefono Generico"));
        return array;
    }
    
    private List<TipoVehiculo> getTipoVehiculoFromDatebase(){
        List<TipoVehiculo> array = new ArrayList<>();
        array.add(new TipoVehiculo(1,"Vehiculo Ejemplo1"));
        array.add(new TipoVehiculo(2,"Vehiculo Ejemplo2"));
        array.add(new TipoVehiculo(3,"Vehiculo Ejemplo3"));
        array.add(new TipoVehiculo(4,"Vehiculo Ejemplo4"));
        return array;
    }
    
    private List<TipoAlojamiento> getTipoAlojamientoFromDatebase(){
        List<TipoAlojamiento> array = new ArrayList<>();
        array.add(new TipoAlojamiento(1,"Alojamiento Ejemplo1"));
        array.add(new TipoAlojamiento(2,"Alojamiento Ejemplo2"));
        array.add(new TipoAlojamiento(3,"Alojamiento Ejemplo3"));
        array.add(new TipoAlojamiento(4,"Alojamiento Ejemplo4"));
        return array;
    }
    
    private List<Alojamiento> getAlojamientoFromDatebase(){
        List<Alojamiento> array = new ArrayList<>();
        array.add(new Alojamiento("1-9","Nombre Agrupacion1", 1, 4, 2));
        array.add(new Alojamiento("2-7","Nombre Agrupacion2", 2, 3, 1));
        array.add(new Alojamiento("3-5","Nombre Agrupacion3", 3, 2, 10));
        array.add(new Alojamiento("4-3","Nombre Agrupacion4", 4,1, 7));
        return array;
    }
    
    private Usuario getUsuarioFromDatabase(String usuario){
        Usuario encontrado = null;
        if(usuario == "JohnTrance")
            encontrado = new Usuario("JohnTrance", "123", "John", "Trance");
        if(usuario == "LindaStewart")
            encontrado = new Usuario("LindaStewart", "123", "Linda", "Stewart");
        if(usuario == "PhilCol")
            encontrado = new Usuario("PhilCol", "123", "Phil", "Col");
        if(usuario == "StanSto")
            encontrado = new Usuario("StanSto", "123", "Stan", "Sto");
        return encontrado;
    }
    private Representante getRepresentanteFromDatabase(String rut){
        Representante encontrado = null;
        if(rut == "1-9")
            encontrado = new Representante("1-9", "Nombre genericos", "Apellidos genericos", "Telefono Generico");
        if(rut == "2-7")
            encontrado = new Representante("2-7", "Nombre genericos", "Apellidos genericos", "Telefono Generico");
        if(rut == "3-5")
            encontrado = new Representante("3-5", "Nombre genericos", "Apellidos genericos", "Telefono Generico");
        if(rut == "4-3")
            encontrado = new Representante("4-3", "Nombre genericos", "Apellidos genericos", "Telefono Generico");
        return encontrado;
    }
    
    private TipoVehiculo getTipoVehiculoFromDatabase(int IdTipoVehiculo){
        TipoVehiculo encontrado = null;
        if(IdTipoVehiculo == 1)
            encontrado = new TipoVehiculo(1,"Vehiculo Ejemplo1");
        if(IdTipoVehiculo == 2)
            encontrado = new TipoVehiculo(2,"Vehiculo Ejemplo2");
        if(IdTipoVehiculo == 3)
            encontrado = new TipoVehiculo(3,"Vehiculo Ejemplo3");
        if(IdTipoVehiculo == 4)
            encontrado = new TipoVehiculo(4,"Vehiculo Ejemplo4");
        return encontrado;
    }
    
    private TipoAlojamiento getTipoAlojamientoFromDatabase(int IdTipoAlojamiento){
        TipoAlojamiento encontrado = null;
        if(IdTipoAlojamiento == 1)
            encontrado = new TipoAlojamiento(1,"Alojamiento Ejemplo1");
        if(IdTipoAlojamiento == 2)
            encontrado = new TipoAlojamiento(2,"Alojamiento Ejemplo2");
        if(IdTipoAlojamiento == 3)
            encontrado = new TipoAlojamiento(3,"Alojamiento Ejemplo3");
        if(IdTipoAlojamiento == 4)
            encontrado = new TipoAlojamiento(4,"Alojamiento Ejemplo4");
        return encontrado;
    }
    
    private Alojamiento getAlojamientoFromDatabase(String rut){
        Alojamiento encontrado = null;
        if(rut == "1-9")
            encontrado = new Alojamiento("1-9","Nombre Agrupacion1", 1, 4, 2);
        if(rut == "2-7")
            encontrado = new Alojamiento("2-7","Nombre Agrupacion2", 2, 3, 1);
        if(rut == "3-5")
            encontrado = new Alojamiento("3-5","Nombre Agrupacion3", 3, 2, 10);
        if(rut == "4-3")
            encontrado = new Alojamiento("4-3","Nombre Agrupacion4", 4,1, 7);
        return encontrado;
    }
    
    private Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
    
    @POST
    @Path("/agregarusuario/{param}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response agregarUsuario(@PathParam("param") String usuario){
        if(usuario == "JohnTrance")
            return Response.ok(gson.toJson("No se puede agregar el usuario " + usuario +" ya existe"), MediaType.APPLICATION_JSON).build();
        if(usuario == "LindaStewart")
            return Response.ok(gson.toJson("No se puede agregar el usuario " + usuario +" ya existe"), MediaType.APPLICATION_JSON).build();
        if(usuario == "PhilCol")
            return Response.ok(gson.toJson("No se puede agregar el usuario " + usuario +" ya existe"), MediaType.APPLICATION_JSON).build();
        if(usuario == "StanSto")
            return Response.ok(gson.toJson("No se puede agregar el usuario " + usuario +" ya existe"), MediaType.APPLICATION_JSON).build();
        else
            return Response.ok(gson.toJson("Usuario agregado correctamente " + usuario ), MediaType.APPLICATION_JSON).build();
    }
    
    @POST
    @Path("/agregarrepresentante/{param}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response agregarRepresentante(@PathParam("param") String rut){
        if(rut == "1-9")
            return Response.ok(gson.toJson("No se puede agregar el representante el rut " + rut +" ya existe"), MediaType.APPLICATION_JSON).build();
        if(rut == "2-7")
            return Response.ok(gson.toJson("No se puede agregar el usuario el rut" + rut +" ya existe"), MediaType.APPLICATION_JSON).build();
        if(rut == "3-5")
            return Response.ok(gson.toJson("No se puede agregar el usuario el rut " + rut +" ya existe"), MediaType.APPLICATION_JSON).build();
        if(rut == "4-3")
            return Response.ok(gson.toJson("No se puede agregar el usuario el rut" + rut +" ya existe"), MediaType.APPLICATION_JSON).build();
        else
            return Response.ok(gson.toJson("Representante agregado rut: " + rut ), MediaType.APPLICATION_JSON).build();
    }
    
    @POST
    @Path("/agregartipovehiculo/{param}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response agregarTipoVehiculo(@PathParam("param") int IdTipoVehiculo){
        if(IdTipoVehiculo == 1)
            return Response.ok(gson.toJson("No se puede agregar el Tipo de vehiculo el id " + IdTipoVehiculo +" ya existe"), MediaType.APPLICATION_JSON).build();
        if(IdTipoVehiculo == 2)
            return Response.ok(gson.toJson("No se puede agregar el Tipo de vehiculo el id " + IdTipoVehiculo +" ya existe"), MediaType.APPLICATION_JSON).build();
        if(IdTipoVehiculo == 3)
            return Response.ok(gson.toJson("No se puede agregar el Tipo de vehiculo el id " + IdTipoVehiculo +" ya existe"), MediaType.APPLICATION_JSON).build();
        if(IdTipoVehiculo == 4)
            return Response.ok(gson.toJson("No se puede agregar el Tipo de vehiculo el id " + IdTipoVehiculo +" ya existe"), MediaType.APPLICATION_JSON).build();
        else
            return Response.ok(gson.toJson("Tipo Vehiculo agregado id: " + IdTipoVehiculo ), MediaType.APPLICATION_JSON).build();
    }
    
    @POST
    @Path("/agregartipoalojamiento/{param}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response agregarTipoAlojamiento(@PathParam("param") int IdTipoAlojamiento){
        if(IdTipoAlojamiento == 1)
            return Response.ok(gson.toJson("No se puede agregar el Tipo de Alojamiento el id " + IdTipoAlojamiento +" ya existe"), MediaType.APPLICATION_JSON).build();
        if(IdTipoAlojamiento == 2)
            return Response.ok(gson.toJson("No se puede agregar el Tipo de Alojamiento el id " + IdTipoAlojamiento +" ya existe"), MediaType.APPLICATION_JSON).build();
        if(IdTipoAlojamiento == 3)
            return Response.ok(gson.toJson("No se puede agregar el Tipo de Alojamiento el id " + IdTipoAlojamiento +" ya existe"), MediaType.APPLICATION_JSON).build();
        if(IdTipoAlojamiento == 4)
           return Response.ok(gson.toJson("No se puede agregar el Tipo de Alojamiento el id " + IdTipoAlojamiento +" ya existe"), MediaType.APPLICATION_JSON).build();
        else
            return Response.ok(gson.toJson("Tipo Alojamiento agregado id: " + IdTipoAlojamiento ), MediaType.APPLICATION_JSON).build();
    }
    
    @POST
    @Path("/agregaralojamiento/{nombre}/{rut}/{idvehiculo}/{idalojamiento}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response agregarAlojamiento(
            @PathParam("nombre") String nombre,
            @PathParam("rut") String rut,
            @PathParam("idvehiculo") int idv,
            @PathParam("idalojamiento") int ida){
        
        if ("1-9".equals(rut) || "2-7".equals(rut) || "3-5".equals(rut) || "4-3".equals(rut)) {
            if (1 == idv || 2 == idv || 3 == idv || 4 == idv) {
                if(1 == ida || 2 == ida || 3 == ida || 4 == ida){
                    return Response.ok(gson.toJson("Alojamiento agregado nombre: " + nombre ), MediaType.APPLICATION_JSON).build();
                }else{
                    return Response.ok(gson.toJson("No se puede agregar Alojamiento El Id de Tipo de Alojamiento " + ida + " no existe"), MediaType.APPLICATION_JSON).build();
                }
            } else {
                return Response.ok(gson.toJson("No se puede agregar Alojamiento El Id de Tipo de vehiculo " + idv + " no existe"), MediaType.APPLICATION_JSON).build();
            }
        }
        return Response.ok(gson.toJson("No se puede agregar el Alojamiento, El rut " + rut + " no existe"), MediaType.APPLICATION_JSON).build();
         
     
    }
    
    @PUT
    @Path("/modificarusuario/{param}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response modificarUsuario(@PathParam("param")String usuario){
        return Response.ok(gson.toJson("Modificado el usuario: " + usuario), MediaType.APPLICATION_JSON).build();
    }
    
    @PUT
    @Path("/modificarrepresentante/{param}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response modificarRepresentante(@PathParam("param")String rut){
        return Response.ok(gson.toJson("Modificado el representante con rut: " + rut), MediaType.APPLICATION_JSON).build();
    }
    
    @PUT
    @Path("/modificartipovehiculo/{param}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response modificarTipoVehiculo(@PathParam("param")int idv){
        return Response.ok(gson.toJson("Modificado el tipo de vehiculo con id: " + idv), MediaType.APPLICATION_JSON).build();
    }
    
    @PUT
    @Path("/modificartipoalojamiento/{param}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response modificarTipoAlojamiento(@PathParam("param")int ida){
        return Response.ok(gson.toJson("Modificado el tipo de alojaminto con rut: " + ida), MediaType.APPLICATION_JSON).build();
    }
    
    @PUT
    @Path("/modificaralojamiento/{param}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response modificarAlojamiento(@PathParam("param")String nombre){
        return Response.ok(gson.toJson("Modificado el alojamiento con nombre: " + nombre), MediaType.APPLICATION_JSON).build();
    }
    
    @DELETE
    @Path("/eliminarusuario/{param}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response eliminarUsuario(@PathParam("param") String usuario){
        return Response.ok(gson.toJson("Eliminado usuario: " + usuario),MediaType.APPLICATION_JSON).build();
    }
    
    @DELETE
    @Path("/eliminarrepresentante/{param}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response eliminarRepresentante(@PathParam("param") String rut){
        return Response.ok(gson.toJson("Eliminado representante rut : " + rut),MediaType.APPLICATION_JSON).build();
    }
    
    @DELETE
    @Path("/eliminartipovehiculo/{param}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response eliminarTipoVehiculo(@PathParam("param") int idv){
        return Response.ok(gson.toJson("Eliminado tipo de vehiculo id : " + idv),MediaType.APPLICATION_JSON).build();
    }
    
    @DELETE
    @Path("/eliminartipoalojamiento/{param}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response eliminarTipoAlojamiento(@PathParam("param") int ida){
        return Response.ok(gson.toJson("Eliminado tipo de alojamiento id : " + ida),MediaType.APPLICATION_JSON).build();
    }
    
    @DELETE
    @Path("/eliminaralojamiento/{param}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response eliminarTipoAlojamiento(@PathParam("param") String nombre){
        return Response.ok(gson.toJson("Eliminado alojamiento nombre : " + nombre),MediaType.APPLICATION_JSON).build();
    }
    
    @GET
    @Path("/consultarusuario")
    @Produces(MediaType.APPLICATION_JSON)
    public Response consultarUsuarios(){
        List<Usuario> array = getUsuarioFromDatebase();
        return Response.ok(gson.toJson(array),MediaType.APPLICATION_JSON).build();
    }
    
    @GET
    @Path("/consultarrepresentante")
    @Produces(MediaType.APPLICATION_JSON)
    public Response consultarRepresentante(){
        List<Representante> array = getRepresentanteFromDatebase();
        return Response.ok(gson.toJson(array),MediaType.APPLICATION_JSON).build();
    }
    
    @GET
    @Path("/consultartipovehiculo")
    @Produces(MediaType.APPLICATION_JSON)
    public Response consultarTipoVehiculos(){
        List<TipoVehiculo> array = getTipoVehiculoFromDatebase();
        return Response.ok(gson.toJson(array),MediaType.APPLICATION_JSON).build();
    }
    
    @GET
    @Path("/consultartipoalojamiento")
    @Produces(MediaType.APPLICATION_JSON)
    public Response consultarTipoAlojamiento(){
        List<TipoAlojamiento> array = getTipoAlojamientoFromDatebase();
        return Response.ok(gson.toJson(array),MediaType.APPLICATION_JSON).build();
    }
    
    @GET
    @Path("/consultaralojamiento")
    @Produces(MediaType.APPLICATION_JSON)
    public Response consultarAlojamiento(){
        List<Alojamiento> array = getAlojamientoFromDatebase();
        return Response.ok(gson.toJson(array),MediaType.APPLICATION_JSON).build();
    }
}
