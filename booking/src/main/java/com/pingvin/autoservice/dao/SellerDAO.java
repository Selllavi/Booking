package com.pingvin.autoservice.dao;

import com.pingvin.autoservice.entity.Parts;
import com.pingvin.autoservice.entity.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public class SellerDAO {

    @Autowired
    private SessionFactory sessionFactory;

    public SellerDAO(){}

    public List<Parts> findOffersByIdUser(User idSeller){
        Session session = this.sessionFactory.getCurrentSession();
        return session.createCriteria(Parts.class).add(Restrictions.eq("idSeller", idSeller)).list();
    }

    public Parts addNewOffer(User idSeller){
        Session session = this.sessionFactory.getCurrentSession();
        if (idSeller.getStatus()!= "ROLE_BLOCKED") {
            if (idSeller.getStatus() == "ROLE_USER")
                idSeller.setStatus("ROLE_SELLER");
            Parts seller = new Parts();
            seller.setIdUser(idSeller);
            session.persist(seller);
            session.flush();
            return seller;
        }
        return null;
    }

    public Parts findSellerByOffer(int idOffer){
        Session session = this.sessionFactory.getCurrentSession();
        return session.get(Parts.class, idOffer);
    }
}
