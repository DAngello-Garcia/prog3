package backtracking;

import backtracking.Model.Elemento;
import backtracking.Model.Maleta;

public class Mochila {
    public static void main(String[] args) {
        Elemento[] elementos = {
                new Elemento(1, 1),
                new Elemento(2, 2),
                new Elemento(4, 10),
                new Elemento(1, 2),
                new Elemento(12, 4)
        };

        Maleta m_base = new Maleta(15, elementos.length);
        Maleta m_opt = new Maleta(15, elementos.length);

        llenarMochila(m_base, elementos, false, m_opt);

        System.out.println(m_opt);
    }

    public static void llenarMochila(Maleta m_base, Elemento[] elementos, boolean llena, Maleta m_opt) {


        if (llena) {

            if (m_base.getBeneficio() > m_opt.getBeneficio()) {

                Elemento[] elementosBase = m_base.getElementos();
                m_opt.clear();


                for (Elemento e : elementosBase) {
                    if (e != null) {
                        m_opt.aniadirElemento(e);
                    }

                }

            }

        } else {

            for (int i = 0; i < elementos.length; i++) {

                if (!m_base.existeElemento(elementos[i])) {
                    if (m_base.getPesoMaximo() > m_base.getPeso() + elementos[i].getPeso()) {
                        m_base.aniadirElemento(elementos[i]); //añadimos
                        llenarMochila(m_base, elementos, false, m_opt);
                        m_base.eliminarElemento(elementos[i]); // lo eliminamos
                    } else {
                        llenarMochila(m_base, elementos, true, m_opt);
                    }

                }

            }

        }

    }
}
