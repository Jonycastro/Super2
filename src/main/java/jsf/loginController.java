/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jsf;

import com.mycompany.super2.modelo.Usuarios;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.view.ViewScoped;
import javax.faces.context.FacesContext;
import jpa.session.UsuariosFacade;
import jpa.session.UsuariosFacadeLocal;

/**
 *
 * @author Jonathan
 */
@Named(value = "loginController")
@SessionScoped
public class loginController implements Serializable {
    @EJB
    private UsuariosFacade usuariosFacade;
    
    
    private Usuarios user;
    /**
     * Creates a new instance of loginControler
     */
    @PostConstruct
    public void init(){
        user = new Usuarios();
    }

    public Usuarios getUser() {
        return user;
    }

    public void setUser(Usuarios user) {
        this.user = user;
    }
    
    public loginController() {
    }
    
    public String verificarUsuario(){
           Usuarios us;
           String direccion = null;
           try{
               us=usuariosFacade.iniciarSesion(user);
               if(us != null){
               direccion="Publico/principal.xhtml?faces-redirect=true";
               } else{
                   FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,"Aviso", "Usuario Incorrecto"));
               }
           }catch(Exception e){
               FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL,"Aviso", "Error"));
           }
           return direccion;
    }
    /**
     * Creates a new instance of NewJSFManagedBean
     */
   
    
}
