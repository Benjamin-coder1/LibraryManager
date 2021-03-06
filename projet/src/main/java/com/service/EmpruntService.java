package com.service;


import java.time.LocalDate;
import java.util.List;
import com.model.*;
 

public interface EmpruntService {
	public List<Emprunt> getList() throws ServiceException;
	public List<Emprunt> getListCurrent() throws ServiceException;
	public List<Emprunt> getListCurrentByMembre(int idMembre) throws ServiceException;
	public List<Emprunt> getListCurrentByLivre(int idLivre) throws ServiceException;
	public Emprunt getById(int id) throws ServiceException;
	public void create(int idMembre, int idLivre, LocalDate dateEmprunt) throws ServiceException;
	public void returnBook(int id) throws ServiceException;
	public int count() throws ServiceException;
	public boolean isLivreDispo(int idLivre) throws ServiceException;
	public boolean isEmpruntPossible(Membre membre) throws ServiceException ;
	public void update(Emprunt emprunt) throws ServiceException ;
}
