package no.oslomet.cs.algdat.Oblig3;


import java.util.*;

public class SBinTre<T> {
    private T verdi;                   // nodens verdi
    private Node<T> venstre, høyre;    // venstre og høyre barn
    private Node<T> forelder;          // forelder

    // konstruktør
    private Node(T verdi, Node<T> v, Node<T> h, Node<T> forelder) {
        this.verdi = verdi;
        venstre = v;
        høyre = h;
        this.forelder = forelder;
    }

    private Node(T verdi, Node<T> forelder)  // konstruktør
    {
        this(verdi, null, null, forelder);
    }

    @Override
    public String toString() {
        return "" + verdi;
    }

} // class Node

    private Node<T> rot;                            // peker til rotnoden
    private int antall;                             // antall noder
    private int endringer;                          // antall endringer

    private final Comparator<? super T> comp;       // komparator

    public SBinTre(Comparator<? super T> c)    // konstruktør
    {
        rot = null;
        antall = 0;
        comp = c;

    }

    public boolean inneholder(T verdi) {
        if (verdi == null) return false;

        Node<T> p = rot;

        while (p != null) {
            int cmp = comp.compare(verdi, p.verdi);
            if (cmp < 0) p = p.venstre;
            else if (cmp > 0) p = p.høyre;
            else return true;
        }

        return false;
    }


    public int antall() {
        return antall;
    }

    public String toStringPostOrder() {
        if (tom()) return "[]";

        StringJoiner s = new StringJoiner(", ", "[", "]");

        Node<T> p = førstePostorden(rot); // går til den første i postorden
        while (p != null) {
            s.add(p.verdi.toString());
            p = nestePostorden(p);
        }

        return s.toString();
    }

    public boolean tom() {
        return antall == 0;
    }

    public int antall() {
        return antall;
    }

    public String toStringPostOrder() {
        if (tom()) return "[]";

        StringJoiner s = new StringJoiner(", ", "[", "]");

        Node<T> p = førstePostorden(rot); // går til den første i postorden
        while (p != null) {
            s.add(p.verdi.toString());
            p = nestePostorden(p);
        }

        return s.toString();
    }

    public boolean tom() {
        return antall == 0;
    }

    public boolean leggInn(T verdi) {
        Objects.requireNonNull(verdi, "Ulovlig med nullverdier!");

        Node<T> p = rot, q = null;
        int cmp = 0;

        while (p != null) {
            q = p;
            cmp = comp.compare(verdi, p.verdi);
            p = cmp < 0 ? p.venstre : p.høyre;
        }


        p = new Node<T>(verdi, q);

        if (q == null) rot = p;
        else if (cmp < 0) q.venstre = p;
        else q.høyre = p;

        antall++;
        return true;
    }

    public boolean fjern(T verdi) {
        throw new UnsupportedOperationException("Ikke kodet ennå!");
    }

    public int fjernAlle(T verdi) {
        throw new UnsupportedOperationException("Ikke kodet ennå!");
    }

    public int antall(T verdi) {
        int teller = 0;

        if (verdi == null) {
            return 0;
        }
        Node<T> p = rot;
        while (p != null) {
            int cmp = comp.compare(verdi, p.verdi);
            if (cmp < 0) {
                p = p.venstre;
            } else if (cmp > 0) {
                p = p.høyre;
            } else {
                teller++;
                p = p.høyre;
            }
        }
        return teller;
    }

    public void nullstill() {
        throw new UnsupportedOperationException("Ikke kodet ennå!");
    }

    private static <T> Node<T> førstePostorden(Node<T> p) {
        while (true) {
            if (p.venstre != null) p = p.venstre;
            else if (p.høyre != null) p = p.høyre;
            else return p;
        }
    }

    private static <T> Node<T> nestePostorden(Node<T> p) {


        Node<T> parent=p.forelder;          //oppretter p sin foreldereNode

        if(parent == null){
            return null;                    //Hvis parent er null så returneres null
        }

        if(parent.høyrebarn==p || parent.høyrebarn==null){
            return parent; //returnerer parent hvis parent sin høyre er p eller parent sin høyre er null
        }
        else{
            return førstePostorden(parent.høyrebarn); // kaller på førstepostorden
        }

        //throw new UnsupportedOperationException("Ikke kodet ennå!");
    }

    public void postorden(Oppgave<? super T> oppgave) {


        Node<T> p= rot;                                 //setter p til å være roten

        Node<T> forst = førstePostorden(p);             // kaller på førstePostorden for p

        oppgave.utførOppgave(forst.verdi);
        while (forst.forelder != null){
            forst= nestePostorden(forst);
            oppgave.utførOppgave(Objects.requireNonNull(forst.verdi));
        }




        //throw new UnsupportedOperationException("Ikke kodet ennå!");
    }

    public void postordenRecursive(Oppgave<? super T> oppgave) {

        postordenRecursive(rot, oppgave);


    }

    private void postordenRecursive(Node<T> p, Oppgave<? super T> oppgave) {



        if (p==null){
            return;                              //returnerer hvis p er null
        }

        postordenRecursive(p.venstrebarn, oppgave);                 //rekursivt kall for p sitt venstrebarn

        postordenRecursive(p.høyrebarn,oppgave);                    //rekursivt kall for p sitt høyrebarn

        oppgave.utførOppgave(p.verdi);





    public void postordenRecursive(Oppgave<? super T> oppgave) {
        postordenRecursive(rot, oppgave);
    }

    private void postordenRecursive(Node<T> p, Oppgave<? super T> oppgave) {
        if (p == null) return;

        postordenRecursive(p.venstre, oppgave);
        postordenRecursive(p.høyre, oppgave);
        oppgave.utførOppgave(p.verdi);
    }

    public ArrayList<T> serialize() {
        throw new UnsupportedOperationException("Ikke kodet ennå!");
    }

    static <K> SBinTre<K> deserialize(ArrayList<K> data, Comparator<? super K> c) {
        throw new UnsupportedOperationException("Ikke kodet ennå!");
    }


} // ObligSBinTre
