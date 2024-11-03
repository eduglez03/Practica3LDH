package es.ull.esit.utils;
import java.util.Objects;

public class Pair<F, S> {
    public final F first;
    public final S second;


    /**
     * Constructor de la clase Pair.
     * @param first Primer elemento del par.
     * @param second Segundo elemento del par.
     */
    public Pair(F first, S second) {
        this.first = first;
        this.second = second;
    }

    /**
     * Método que devuelve el primer elemento del par.
     * @return Primer elemento del par.
     */
    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Pair)) {
            return false;
        }
        Pair<?, ?> p = (Pair<?, ?>) o;
        return Objects.equals(p.first, first) && Objects.equals(p.second, second);
    }

    /**
     * Método que devuelve el hashcode del par.
     * @return Hashcode del par.
     */
    @Override
    public int hashCode() {
        return (first == null ? 0 : first.hashCode()) ^ (second == null ? 0 : second.hashCode());
    }

    /**
     * Método que devuelve el hashcode del par.
     * @return Hashcode del par.
     */
    public static <A, B> Pair <A, B> create(A a, B b) {
        return new Pair<A, B>(a, b);
    }
}