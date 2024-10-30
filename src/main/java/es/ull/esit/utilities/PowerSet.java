package es.ull.esit.utilities;

import java.util.*;

// Sirve para calcular todos los subconjuntos de un conjunto dado
public class PowerSet<E> implements Iterator<Set<E>>, Iterable<Set<E>> {

    private E[] arr = null;
    private BitSet bset = null;

    @SuppressWarnings("unchecked")
    public PowerSet(Set<E> set) {
        this.arr = (E[]) set.toArray();
        this.bset = new BitSet(this.arr.length + 1);
    }

    @Override
    public boolean hasNext() {
        return !this.bset.get(this.arr.length);
    }

    @Override
    public Set<E> next() {
        if (!hasnext()) {
            throw new NoSuchElementException("No more elements in the collection.");
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

    public boolean hasnext() {
        // Lógica para verificar si quedan elementos por iterar
        // Esto depende de cómo defines la condición de finalización para tu iterador
        return !this.bset.isEmpty(); // Ejemplo; ajustar según sea necesario
    }


    @Override
    public void remove() {
        throw new UnsupportedOperationException("Not Supported!");
    }

    @Override
    public Iterator<Set<E>> iterator() {
        return this.iterator();
    }
}