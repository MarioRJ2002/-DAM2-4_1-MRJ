class Módulo (maxAlumnos:Int){
    var alumnos = arrayOfNulls<Alumno>(maxAlumnos)
    var evaluaciones = Array(alumnos.size) {arrayOfNulls<Float>(4)}

    fun añadirAlumnos(alumno: Alumno):Boolean{
        if(null in alumnos) {
            alumnos[alumnos.indexOfFirst { it == null }] = alumno
            return true
        }
        else {
            return false
        }
    }

    fun calcularEvaluacionFinal(){
        alumnos.forEach{ al->
            if (al!=null){
                var numAlumn:Int = alumnos.indexOfFirst{ it?.nombre == al?.nombre}
                evaluaciones[numAlumn][3]=0.0F
                if(null in evaluaciones[numAlumn]){
                }else{
                    var media = 0.0F
                    for(i in 0..3){media==evaluaciones[numAlumn][i]}
                    evaluaciones[3][numAlumn]=media/3
                }
            }
        }
    }

    fun establecerNota(idAlumno: String, evaluacion:String, Nota:Float):Boolean{
        var isIntroduced = false
        if (evaluacion in EVALUCION_PRIMERA..EVALUCION_TERCERA) {
            evaluaciones[evaluacion.toInt()][alumnos.indexOfFirst { it?.id == idAlumno }]
            isIntroduced = true
        }
        return isIntroduced
    }

    companion object{
        val EVALUCION_PRIMERA="0"
        val EVALUCION_SEGUNDA="1"
        val EVALUCION_TERCERA="2"
        val EVALUCION_FINAL="3"
    }
}