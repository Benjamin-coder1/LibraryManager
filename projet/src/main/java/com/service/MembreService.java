package com.service;


import java.util.List;
import com.model.*;

public interface MembreService {

	public List<Membre> getList() throws ServiceException;
	public List<Membre> getListMembreEmpruntPossible() throws ServiceException;
	public Membre getById(int id) throws ServiceException;
	public int create(String nom, String prenom, String adresse, String email, String telephone) throws ServiceException;
	public void update(Membre membre) throws ServiceException;
	public void delete(int id) throws ServiceException;
	public int count() throws ServiceException;

}
