/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jpa.session;

import com.mycompany.super2.modelo.Usuarios;
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
public class UsuariosFacade extends AbstractFacade<Usuarios> {

    @PersistenceContext(unitName = "com.mycompany_super2_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UsuariosFacade() {
        super(Usuarios.class);
    }
    
    /**
     *
     * @param us
     * @return
     */
    public Usuarios iniciarSesion(Usuarios us){
        Usuarios user = null;
        String consulta;
        try{
            consulta = "FROM Usuarios u WHERE u.nombre = ?1 and u.contraseña = ?2";
            Query query = em.createQuery(consulta);
            query.setParameter(1, us.getNombre());
            query.setParameter(2, us.getContraseña());
            List<Usuarios> lista = query.getResultList();
            if(!lista.isEmpty()){
                user=lista.get(0);
            }
        }catch (Exception e){
            throw e;
        }
        return user;
    }
}
