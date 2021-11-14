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
            evaluaciones[alumnos.indexOfFirst { it?.id == idAlumno }][evaluacion.toInt()]
            isIntroduced = true
        }
        return isIntroduced
    }

    fun listaNotas(evaluacion: String):List<Pair<String,Float>> {
        var lista = mutableListOf<Pair<String,Float>>(Pair("-1",-1.0F))
        if (evaluacion in EVALUCION_PRIMERA..EVALUCION_FINAL) {
            for(it in 0..alumnos.size-1){
                    lista[it] = Pair<String, Float>(alumnos[it]!!.id, evaluaciones[it][evaluacion.toInt()] as Float)

            }
        }
        return lista
    }

    fun numeroAprobador(evaluacion: String):Int {
        var contAprobados = 0
        if (evaluacion in EVALUCION_PRIMERA..EVALUCION_FINAL) {
            evaluaciones.forEach { if (it[evaluacion.toInt()]!=null && it[evaluacion.toInt()]!! >=5.0F){contAprobados++}}
         }
        return contAprobados
    }

    fun notaMasAlta(evaluacion: String):Float{
        var maxNum=-1.0F
        if (evaluacion in EVALUCION_PRIMERA..EVALUCION_FINAL) {
            evaluaciones.forEach { if (it[evaluacion.toInt()]!=null && it[evaluacion.toInt()]!! >= maxNum){maxNum=it[evaluacion.toInt()]!!}}
        }
        return maxNum
    }

    fun notaMasBaja(evaluacion: String):Float{
        var minNum=11.0F
        if (evaluacion in EVALUCION_PRIMERA..EVALUCION_FINAL) {
            evaluaciones.forEach { if (it[evaluacion.toInt()]!=null && it[evaluacion.toInt()]!! <= minNum){minNum=it[evaluacion.toInt()]!!}}
        }
        return minNum
    }

    fun hayAlumnosConDiez(evaluacion: String):Boolean{
        var hayDiez = false
        if (evaluacion in EVALUCION_PRIMERA..EVALUCION_FINAL) {
            evaluaciones.forEach { if(it[evaluacion.toInt()]==10.0F){hayDiez=true}}
        }
        return hayDiez
    }

    fun hayAlumnosAprobados(evaluacion: String):Boolean{
        var hayAprobados = false
        if (evaluacion in EVALUCION_PRIMERA..EVALUCION_FINAL) {
            evaluaciones.forEach { if(it[evaluacion.toInt()]!=null && it[evaluacion.toInt()]!!>=5F){hayAprobados=true}}
        }
        return hayAprobados
    }

    fun notaMedia(evaluacion: String):Float{
        var notaMedia = 0.0F
        var cont = 0
        if (evaluacion in EVALUCION_PRIMERA..EVALUCION_FINAL) {
            evaluaciones.forEach { if(it[evaluacion.toInt()]!=null){
                notaMedia = notaMedia + it[evaluacion.toInt()] as Float
                cont++
            }
            }
        }
        return notaMedia/cont
    }

    fun primeraNotaNoAprobada(evaluacion: String):Float{
        var primeraNota = 0F
        if (evaluacion in EVALUCION_PRIMERA..EVALUCION_FINAL) {
            evaluaciones.forEach { if (it[evaluacion.toInt()]!=null && it[evaluacion.toInt()]!! >= primeraNota && it[evaluacion.toInt()]!!<=5){
                primeraNota=it[evaluacion.toInt()]!!
            }
            }
        }
        return primeraNota
    }

    fun listaNotasOrdenados(evaluacion: String):List<Pair<String,Float>> {
        var lista = mutableListOf<Pair<String,Float>>(Pair("-1",-1.0F))
        var alumnosOrdenados = arrayOfNulls<String>(alumnos.size)
        var notasOrdenadas = arrayOfNulls<Float>(alumnos.size)
        if (evaluacion in EVALUCION_PRIMERA..EVALUCION_FINAL){
            for(i in 0..evaluaciones.size-1){
                notasOrdenadas[i] = evaluaciones[i][evaluacion.toInt()]
            }
            notasOrdenadas.sort()
            for(i in 0..evaluaciones.size-1){
                alumnosOrdenados[i] = alumnos[evaluaciones.indexOfFirst{it[evaluacion.toInt()] == notasOrdenadas[i]}]?.id
            }
        }
        for (i in 0..evaluaciones.size-1) {
            if (alumnosOrdenados[i] != null && notasOrdenadas[i] != null) {
                lista[i] = Pair(alumnosOrdenados[i], notasOrdenadas[i]) as Pair<String, Float>
            }
        }
        return lista
    }

    companion object{
        val EVALUCION_PRIMERA="0"
        val EVALUCION_SEGUNDA="1"
        val EVALUCION_TERCERA="2"
        val EVALUCION_FINAL="3"
    }
}
