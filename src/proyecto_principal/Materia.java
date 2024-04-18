package proyecto_principal;



public class Materia {

    public Materia(String nombre) {
        this.nombre = nombre;
    }

    public int[] getPonderaciones() {
        return ponderaciones;
    }

    public String[] getCriterios() {
        return criterios;
    }

    public void setCriterios(String[] criterios) {
        this.criterios = criterios;
    }

    public String getNombre() {
        return nombre;
    }

    public void sugestiones(){
	sugestiones = new String[ponderaciones.length];	
	for (int i = 0; i < ponderaciones.length; i++){
		float operacion = ((float)ponderaciones[i] / 100) * Introduccion.getCalificacion_deseada();
		sugestiones[i] = "para "+criterios[i]+" necesitas obtener "+(int) operacion+" sobre los "+ponderaciones[i]+ " puntos originales";
	}	
    }
    public String[] sugestiones;
    public int[] ponderaciones;
    public String[] criterios;
    private String nombre;
}
