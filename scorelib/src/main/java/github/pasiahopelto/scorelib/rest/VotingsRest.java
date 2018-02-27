package github.pasiahopelto.scorelib.rest;

import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import github.pasiahopelto.scorelib.fetcher.VotingsLister;
import github.pasiahopelto.scorelib.model.Voting;

@RestController
public class VotingsRest {

	private JdbcTemplate jdbcTemplate;
	private VotingsLister votingsLister;

	public VotingsRest(JdbcTemplate jdbcTemplate, VotingsLister votingsLister) {
		this.jdbcTemplate = jdbcTemplate;
		this.votingsLister = votingsLister;
	}

	@RequestMapping("/votings")
	public List<Voting> votings() {
		return votingsLister.listVotings(jdbcTemplate);
	}
}
