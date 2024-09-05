package com.myshoppingcart.persistence;

import com.myshoppingcart.exception.UsuarioNotFoundException;
import com.myshoppingcart.model.Usuario;
import lombok.Setter;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.nio.file.attribute.UserPrincipalNotFoundException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Setter
@Repository
public class UsuarioDBRepository implements IUsuarioRepository {
    private String db_url = null;

    @PersistenceContext
    EntityManager entityManager;


    @Override
    @Transactional
    public boolean existeUsuario(String email, String pass) throws Exception {
        String jpql = "SELECT u From Usuario u where u.email = :email AND u.pass = :pass";
        TypedQuery<Usuario> query = entityManager.createQuery(jpql, Usuario.class);
        query.setParameter("email", email);
        query.setParameter("password", pass);
        String usuarios = query.getSingleResult().toString();
        return !usuarios.isEmpty();
    }

    @Override
    @Transactional
    public Usuario getUsuario(String email, String pass) throws UsuarioNotFoundException, Exception {
        String jpql = "SELECT u From Usuario u where u.email = :email AND u.pass = :pass";
        TypedQuery<Usuario> query = entityManager.createQuery(jpql, Usuario.class);
        query.setParameter("email", email);
        query.setParameter("password", pass);

        return query.getResultList().stream().findFirst().orElseThrow();
    }

    public List<Usuario> getUsuarios(String iniciales) throws Exception {
        String jpql = "Select u From Usuario u where u.nombre LIKE :iniciales";
        TypedQuery<Usuario> query = entityManager.createQuery(jpql, Usuario.class);
        query.setParameter("iniciales", "%" + iniciales + "%");
        return query.getResultList();
    }

    @Override
    @Transactional
    public Usuario insertUsuario(Usuario nuevoUsuario) throws Exception {
        entityManager.persist(nuevoUsuario);
        return nuevoUsuario;
    }

    @Override
    @Transactional
    public Usuario updateUsuario(Usuario unUsuario) throws Exception {
        if (entityManager.find(Usuario.class, unUsuario.getUid()) == null) {
            throw new UsuarioNotFoundException();
        }
        return entityManager.merge(unUsuario);
    }

    @Override
    @Transactional
    public boolean deleteUsuario(Integer uid) throws Exception {
        Usuario usuario = entityManager.find(Usuario.class, uid);
        if (usuario == null) {
            throw new UsuarioNotFoundException();
        }
        entityManager.remove(usuario);
        return true;
    }
}
