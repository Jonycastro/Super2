/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jsf;

import com.mycompany.super2.modelo.Administrador;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import jpa.session.AdministradorFacade;
import jpa.session.AdministradorFacadeLocal;

/**
 *
 * @author Jonathan
 */
@Named(value = "adminController")
@SessionScoped
public class adminController implements Serializable {

    @EJB
    private AdministradorFacade administradorFacade;
    
    
    private Administrador admin;
    /**
     * Creates a new instance of adminController
     */
    @PostConstruct
    public void init(){
        admin = new Administrador();
    }
    
    public adminController() {
    }

    public Administrador getAdmin() {
        return admin;
    }

    public void setAdmin(Administrador admin) {
        this.admin = admin;
    }
    
    public String verificarAdmin(){
           Administrador ad;
           String direccion = null;
           try{
               ad=administradorFacade.iniciarSesion(admin);
               if(ad != null){
               direccion="Privado/accionesAdmin.xhtml?faces-redirect=true";
               }else{
                   FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,"Aviso", "Admin Incorrecto"));
               }
           }catch(Exception e){
               FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL,"Aviso", "Error"));
           }
           return direccion;
    }
}
