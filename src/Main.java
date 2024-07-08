
public class Main {
    public static void main(String[] args) {
        ArbolBinario arbol = new ArbolBinario();

        // Insertar valores en el árbol
        arbol.insertar("Mango");
        arbol.insertar("Manzana");
        arbol.insertar("Naranja");
        arbol.insertar("Banana");
        arbol.insertar("Uva");

        // Mostrar el recorrido InOrder
        System.out.println("Recorrido InOrder:");
        arbol.recorrerInOrder();

        // Mostrar el recorrido PreOrden
        System.out.println("Recorrido PreOrden:");
        arbol.recorrerPreOrden();

        // Mostrar el recorrido PostOrden
        System.out.println("Recorrido PostOrden:");
        arbol.recorrerPostOrden();

        // Eliminar un valor del árbol
        System.out.println("Eliminando 'Naranja':");
        arbol.eliminar("Naranja");

        // Mostrar el recorrido InOrder después de la eliminación
        System.out.println("Recorrido InOrder después de eliminar 'Naranja':");
        arbol.recorrerInOrder();
    }
}