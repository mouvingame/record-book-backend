package by.loiko.recordbookbackend.controllers;

import by.loiko.recordbookbackend.model.Human;
import by.loiko.recordbookbackend.service.HumanService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Positive;

@RestController
@RequestMapping("api/recordbook/humans")
@Validated
public class HumanController {
    private final HumanService humanService;

    public HumanController(HumanService humanService) {
        this.humanService = humanService;
    }

    @PostMapping
    public Human create(@RequestBody Human newest) {
        return humanService.create(newest);
    }

    @GetMapping
    public Iterable<Human> getHumans() {
        return humanService.getAll();
    }

    @GetMapping("{id}")
    public Human getHuman(@PathVariable @Positive long id) {
        return humanService.getHuman(id);
    }

    @PutMapping("{id}")
    public Human updateHuman(@RequestBody Human updater, @PathVariable @Positive long id) {
        return humanService.updateHuman(updater, id);
    }

    @DeleteMapping("{id}")
    public void deleteHuman(@PathVariable @Positive long id) {
        humanService.deleteHuman(id);
    }
}
