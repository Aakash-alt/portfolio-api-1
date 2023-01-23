package com.univ.backend.services;

import com.univ.backend.entities.TeamEntity;
import com.univ.backend.exceptions.MandatoryFieldFoundEmptyException;
import com.univ.backend.exceptions.TeamMemberNotFoundException;
import com.univ.backend.repositories.TeamRepository;
import com.univ.backend.response.TeamPostRequestResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.List;
import java.util.Optional;

@Service
public class TeamServiceImpl implements TeamService {

    @Autowired
    public TeamRepository repository;

    @Override
    public TeamPostRequestResponse addTeam(TeamEntity entity) throws MandatoryFieldFoundEmptyException {
        if (entity.getName() != null && entity.getRole() != null && entity.getInfo() != null && entity.getImage() != null && entity.getImage() != null) {
            TeamEntity saved = repository.save(entity);
            return new TeamPostRequestResponse(
                    HttpStatus.OK,
                    saved,
                    entity.getName() + " posted in database successfully!",
                    Calendar.getInstance().getTimeInMillis()
            );
        }
        throw new MandatoryFieldFoundEmptyException("Mandatory fields must be filled!");
    }

    @Override
    public List<TeamEntity> getTeams() {
        return repository.findAll();
    }

    @Override
    public TeamEntity getTeamByUrl(String url) throws TeamMemberNotFoundException {
        Optional<TeamEntity> optionalTeam = repository.findByUrl(url);
        if (optionalTeam.isEmpty()) {
            throw new TeamMemberNotFoundException("Team member with that URL not found!");
        }
        return optionalTeam.get();
    }
}
