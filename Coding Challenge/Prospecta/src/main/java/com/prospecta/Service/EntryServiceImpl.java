package com.prospecta.Service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.prospecta.Exceptions.EntryException;
import com.prospecta.Model.Data;
import com.prospecta.Model.Entry;
import com.prospecta.Model.EntryEntity;
import com.prospecta.Model.ResponseDTO;
import com.prospecta.Repository.EntryEntityRepo;

@Service
public class EntryServiceImpl implements EntryService {
	@Value("${external.api}")
	private String url;

	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private EntryEntityRepo eEntityRepo;

	@Override
	public Data consumeExternalApi() {
		Data data = restTemplate.getForObject(url, Data.class);
		List<Entry> list = data.getEntries();
		data.setCount(list.size());
		return data;
	}

	@Override
	public List<ResponseDTO> searchByCategory(String category) throws EntryException {
		Data data = restTemplate.getForObject(url, Data.class);
		List<Entry> list = data.getEntries();
		List<ResponseDTO> dto = new ArrayList<>();

		for (Entry entry : list) {

			if (entry.getCategory().contains(" ")) {
				String[] str = entry.getCategory().split(" ");
				String categoryFind = str[0];
				if (categoryFind.equals(category) || categoryFind.toLowerCase().equals(category.toLowerCase())) {
					dto.add(new ResponseDTO(entry.getApi(), entry.getDescription()));
				}

			} else if (entry.getCategory().contains(" &")) {
				String[] str = entry.getCategory().split(" &");
				String categoryFind = str[0];
				if (categoryFind.equals(category) || categoryFind.toLowerCase().equals(category.toLowerCase())) {
					dto.add(new ResponseDTO(entry.getApi(), entry.getDescription()));
				}
			} else if (entry.getCategory().equals(category)
					|| entry.getCategory().toLowerCase().equals(category.toLowerCase())) {

				dto.add(new ResponseDTO(entry.getApi(), entry.getDescription()));
			}
		}

		if (dto.isEmpty())
			throw new EntryException("Haven't found any result from this category " + category);
		else
			return dto;

	}

	@Override
	public Data registerNewEntry(Entry entry) throws EntryException {
		Data data = restTemplate.getForObject(url, Data.class);
		List<Entry> list = data.getEntries();

		if (entry != null) {
			for (Entry en : list) {
				if (en.getApi().equals(entry.getApi()))
					throw new EntryException("Please change the API this api is already register : " + entry.getApi());
				if (en.link.equals(entry.getLink()))
					throw new EntryException("This url is already register : " + entry.getLink());
			}
			list.add(entry);
			data.setCount(list.size());
		}
		return data;
	}

	@Override
	public EntryEntity registerNewEntryDatabase(EntryEntity entry) throws EntryException {
		if(eEntityRepo.existsByApi(entry.getApi())) throw new EntryException("Please change The Api name This api is already Registered : "+entry.getApi());
		if(eEntityRepo.existsByLink(entry.getLink())) throw new EntryException("Please change The Link, This Link is already registered : "+entry.getLink());
		return eEntityRepo.save(entry);
	}

}
