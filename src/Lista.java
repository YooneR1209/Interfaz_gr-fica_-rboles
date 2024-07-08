public abstract class Lista implements AccionNodo{
        //Atributos
    private String nodoCabeza;
    private String dato;
        //Relaciones

        //Constructor

        //Metodos
    public int agregarRama(){
        return 0;
    }

    public int insertarRama(){
        return 0;
    }

    public int eliminarRama(){
        return 0;
    }

    public int recorrerRama(){
        return 0;
    }

    public void siguiente(){

    }

//Interface --> metodos.
    @Override
    public boolean agregarNodo(){
        return false;
    }

    @Override
    public boolean eliminarNodo(){
        return false;
    }

    @Override
    public boolean insertarNodo(){
        return false;
    }
}
