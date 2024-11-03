package es.ull.esit.utilities;

import java.util.*;

/**
 * Clase que implementa un iterador para obtener el conjunto potencia de un conjunto dado.
 * @param <E> Tipo de los elementos del conjunto.
 */
public class PowerSet<E> implements Iterator<Set<E>>, Iterable<Set<E>> {

    private E[] arr = null;
    private BitSet bset = null;

    @SuppressWarnings("unchecked")
    public PowerSet(Set<E> set) {
        this.arr = (E[]) set.toArray();
        this.bset = new BitSet(this.arr.length + 1);
    }

    /**
     * Método que comprueba si hay más elementos en el conjunto potencia.
     * @return true si hay más elementos, false en caso contrario.
     */
    @Override
    public boolean hasNext() {
        return !this.bset.get(this.arr.length);
    }

    /**
     * Método que devuelve el siguiente conjunto del conjunto potencia.
     * @return Conjunto del conjunto potencia.
     */
    @Override
    public Set<E> next() {
        if (!hasNext()) {
            throw new NoSuchElementException("No hay más elementos.");
        }

        Set<E> returnSet = new TreeSet<>();
        for (int i = 0; i < this.arr.length; i++) {
            if (this.bset.get(i)) {
                returnSet.add(this.arr[i]);
            }
        }

        for (int i = 0; i < this.bset.size(); i++) {
            if (!this.bset.get(i)) {
                this.bset.set(i);
                break;
            } else {
                this.bset.clear(i);
            }
        }
        return returnSet;
    }


    /**
     * Método que elimina un elemento del conjunto potencia.
     */
    @Override
    public void remove() {
        throw new UnsupportedOperationException("Not Supported!");
    }

    /**
     * Método que devuelve un iterador para recorrer el conjunto potencia.
     * @return Iterador del conjunto potencia.
     */
    @Override
    public Iterator<Set<E>> iterator() {
        return new PowerSet<>(Set.of(arr)); // nueva instancia de PowerSet para cada recorrido
    }

}