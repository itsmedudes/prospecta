package com.prospecta.Service;

import java.util.List;

import com.prospecta.Exceptions.EntryException;
import com.prospecta.Model.Data;
import com.prospecta.Model.Entry;
import com.prospecta.Model.EntryEntity;
import com.prospecta.Model.ResponseDTO;

public interface EntryService {
	public Data consumeExternalApi();
	public List<ResponseDTO> searchByCategory(String category) throws EntryException;
	public Data registerNewEntry(Entry entry) throws EntryException;
	public EntryEntity registerNewEntryDatabase(EntryEntity entry) throws EntryException;
}
