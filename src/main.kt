
fun main(){
      var mod = Módulo()
      var alumno1 = Alumno("Juan","Juanito Juan")
      mod.añadirAlumnos(alumno1)
      println(mod.alumnos[0]!!.nombre.id)
}
