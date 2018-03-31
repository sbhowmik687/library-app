package edu.utdallas.sxb170035.library_app.data.jpa.repository;

import java.io.Serializable;
import java.sql.*;
import java.util.Random;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SessionImplementor;
import org.hibernate.id.IdentifierGenerator;

public class BorrowerIdentifier implements IdentifierGenerator{

    @Override
    public Serializable generate(SessionImplementor session, Object object)
            throws HibernateException {

        String prefix = "ID";
        Connection connection = session.connection();

        try {
            Statement statement=connection.createStatement();

            ResultSet rs=statement.executeQuery("select count(borrower.card_id) as Id from borrower");

            if(rs.next())
            {
            	Random rand = new Random();
            	int  n = rand.nextInt(500) + 1;
                //int id=rs.getInt(1)+100001;
            	int id=n+100001;
                String generatedId = prefix + new Integer(id).toString();
                System.out.println("Generated Id: " + generatedId);
                return generatedId;
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }


        return null;
    }

}