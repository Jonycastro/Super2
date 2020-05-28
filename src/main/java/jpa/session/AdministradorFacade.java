    /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jpa.session;

import com.mycompany.super2.modelo.Administrador;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Jonathan
 */
@Stateless
public class AdministradorFacade extends AbstractFacade<Administrador> {

    @PersistenceContext(unitName = "com.mycompany_super2_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public AdministradorFacade() {
        super(Administrador.class);
    }
    
    /**
     *
     * @param ad
     * @return
     */
    
    public Administrador iniciarSesion(Administrador ad){
        Administrador admin = null;
        String consulta;
        try{
            consulta = "FROM Administrador a WHERE a.nombre = ?1 and a.contraseña = ?2";
            Query query = em.createQuery(consulta);
            query.setParameter(1, ad.getNombre());
            query.setParameter(2, ad.getContraseña());
            List<Administrador> lista = query.getResultList();
            if(!lista.isEmpty()){
                admin=lista.get(0);
            }
        }catch (Exception e){
            throw e;
        }
        return admin;
    }
}
