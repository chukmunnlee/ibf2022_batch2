package ibf2022.batch2.paf.day27.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ibf2022.batch2.paf.day27.models.Comment;
import ibf2022.batch2.paf.day27.models.TvShow;
import ibf2022.batch2.paf.day27.repositories.TvShowRepository;

@Service
public class TvShowService {

	@Autowired
	private TvShowRepository tvshowRepo;

	public Optional<TvShow> find(String showName) {
		List<TvShow> results = tvshowRepo.findTvShowByName(showName);
		if (results.isEmpty())
			return Optional.empty();
		// return the first result
		return Optional.of(results.get(0));
	}

	public void insertComment(Comment comment) {
		tvshowRepo.insertComment(comment);
	}
}
