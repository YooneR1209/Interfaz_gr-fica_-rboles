public class ArbolBinario {
    private Nodo nodoRaiz;

    public ArbolBinario() {
        nodoRaiz = null;
    }

    public void insertar(String valor) {
        nodoRaiz = insertarRec(nodoRaiz, valor);
    }

    private Nodo insertarRec(Nodo nodo, String valor) {
        if (nodo == null) {
            nodo = new Nodo(valor);
            return nodo;
        }
        if (valor.compareTo(nodo.valor) < 0)
            nodo.izquierdo = insertarRec(nodo.izquierdo, valor);
        else if (valor.compareTo(nodo.valor) > 0)
            nodo.derecho = insertarRec(nodo.derecho, valor);

        return nodo;
    }

    public void eliminar(String valor) {
        nodoRaiz = eliminarRec(nodoRaiz, valor);
    }

    private Nodo eliminarRec(Nodo nodo, String valor) {
        if (nodo == null) return nodo;

        if (valor.compareTo(nodo.valor) < 0)
            nodo.izquierdo = eliminarRec(nodo.izquierdo, valor);
        else if (valor.compareTo(nodo.valor) > 0)
            nodo.derecho = eliminarRec(nodo.derecho, valor);
        else {
            if (nodo.izquierdo == null)
                return nodo.derecho;
            else if (nodo.derecho == null)
                return nodo.izquierdo;

            nodo.valor = minValor(nodo.derecho);
            nodo.derecho = eliminarRec(nodo.derecho, nodo.valor);
        }

        return nodo;
    }

    private String minValor(Nodo nodo) {
        String minVal = nodo.valor;
        while (nodo.izquierdo != null) {
            nodo = nodo.izquierdo;
            minVal = nodo.valor;
        }
        return minVal;
    }

    public void agregar(String valor) {
        insertar(valor);
    }

    public void recorrerInOrder() {
        recorrerInOrderRec(nodoRaiz);
        System. out.println();
    }

    private void recorrerInOrderRec(Nodo nodo) {
        if (nodo != null) {
            recorrerInOrderRec(nodo.izquierdo);
            System.out.print(nodo.valor + " ");
            recorrerInOrderRec(nodo.derecho);
        }
    }

    public void recorrerPreOrden() {
        recorrerPreOrdenRec(nodoRaiz);
        System.out.println();
    }

    private void recorrerPreOrdenRec(Nodo nodo) {
        if (nodo != null) {
            System.out.print(nodo.valor + " ");
            recorrerPreOrdenRec(nodo.izquierdo);
            recorrerPreOrdenRec(nodo.derecho);
        }
    }

    public void recorrerPostOrden() {
        recorrerPostOrdenRec(nodoRaiz);
        System.out.println();
    }

    private void recorrerPostOrdenRec(Nodo nodo) {
        if (nodo != null) {
            recorrerPostOrdenRec(nodo.izquierdo);
            recorrerPostOrdenRec(nodo.derecho);
            System.out.print(nodo.valor + " ");
        }
    }
}