package br.com.bots.service;

import br.com.bots.dto.BotDTO;
import br.com.bots.model.Bot;
import br.com.bots.repository.BotRepository;
import org.modelmapper.MappingException;
import org.modelmapper.ModelMapper;
import org.modelmapper.spi.ErrorMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class BotService {

    @Autowired
    private ModelMapper modelMapper;
    private BotRepository botRepository;

    private static final Logger LOGGER = LoggerFactory.getLogger(BotService.class);

    public BotService(BotRepository botRepository) {
        this.botRepository = botRepository;
    }

    public ResponseEntity getAll() {
        List<Bot> bots = botRepository.findAll();
        if (!bots.isEmpty()) {
            LOGGER.error("No bots founded.");
            return ResponseEntity.badRequest().body("No bots founded.");
        }
        return ResponseEntity.ok(bots);
    }

    public ResponseEntity save(BotDTO botDTO) {
        if (botDTO.getId() != null) {
            return ResponseEntity.badRequest().body("You cannot set an id, id is autogenerated.");
        }

        botDTO.setId(UUID.randomUUID().toString());
        Bot bot = convertToEntity(botDTO);

        try {
            botRepository.save(bot);
            return ResponseEntity.ok("Bot has been inserted sucessfully");
        } catch (Exception e) {
            LOGGER.error("Fail to insert bot, error: ".concat(e.getMessage()));
            return ResponseEntity.badRequest().body("Error to insert bot.");
        }
    }

    public ResponseEntity update(BotDTO botDTO) {
        if (botDTO.getId() == null) {
            return ResponseEntity.badRequest().body("Id cannot be null");
        }

        Bot bot = convertToEntity(botDTO);
        try {
            botRepository.save(bot);
            return ResponseEntity.ok("Bot has been updated sucessfully");
        } catch (Exception e) {
            LOGGER.error("Fail to insert bot, error: ".concat(e.getMessage()));
            return ResponseEntity.badRequest().body("Error to update bot.");
        }
    }

    public void update(Bot bot) {
        if (bot.getId() == null) {
            LOGGER.error("Id cannot be null");
        }
        botRepository.save(bot);
    }

    public ResponseEntity<String> delete(String id) {
        if (id == null) {
            LOGGER.error("Id cannot be null");
        }
        try {
            botRepository.deleteById(id);
            return ResponseEntity.ok("Bot has been deleted sucessfuly");
        } catch (Exception e) {
            LOGGER.error("Update failed, error: ".concat(e.getMessage()));
            return ResponseEntity.badRequest().body("Error to delete bot.");
        }
    }

    public ResponseEntity findById(String id) {
        Optional<Bot> bot = botRepository.findById(id);
        if (!bot.isPresent()) {
            LOGGER.error("Cannot find any bot with id: ".concat(id));
            return ResponseEntity.badRequest().body("Cannot find any bot with id: ".concat(id));
        }
        LOGGER.info("Bot id: ".concat(id).concat(" localizado."));
        return ResponseEntity.ok(bot);
    }

    public BotDTO convertToDTO(Bot bot) throws MappingException {
        try {
            BotDTO clienteDTO = modelMapper.map(bot, BotDTO.class);
            return clienteDTO;
        } catch (MappingException e) {
            LOGGER.error("Erro de-para objetos.", e.getMessage());
            throw new MappingException((List<ErrorMessage>) e.getErrorMessages());
        }
    }

    public Bot convertToEntity(BotDTO botDTO) {
        try {
            Bot bot = modelMapper.map(botDTO, Bot.class);
            return bot;
        } catch (MappingException e) {
            LOGGER.error("Erro de-para objetos.", e.getMessage());
            throw new MappingException((List<ErrorMessage>) e.getErrorMessages());
        }
    }


}
