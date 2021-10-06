package com.aca_disqo.generactive.controller;

import com.aca_disqo.generactive.controller.dto.ItemDTO;
import com.aca_disqo.generactive.converter.ItemConverter;
import com.aca_disqo.generactive.service.ItemService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController("/item")
public class ItemController {

    private final ItemService itemService;
    private final ItemConverter itemConverter;

    public ItemController(ItemService itemService, ItemConverter itemConverter) {
        this.itemService = itemService;
        this.itemConverter = itemConverter;
    }

    @GetMapping()
    public List<? extends ItemDTO> getAll() {
        return itemConverter.bulkConvert(itemService.findALl());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ItemDTO> getItemById(@PathVariable Long id){
        return ResponseEntity.ok(itemConverter.convert(itemService.findItemBYId(id)));
    }

    @PostMapping()
    public ResponseEntity<ItemDTO> create(@RequestBody ItemDTO itemDTO){
        return ResponseEntity.ok(itemConverter.convert(itemService.create(itemDTO)));
    }

    @PutMapping()
    public ResponseEntity<ItemDTO> update(@PathVariable Long id, @RequestBody ItemDTO itemDTO){
        return ResponseEntity.ok(itemConverter.convert(itemService.update(id,itemDTO)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable Long id){
        return ResponseEntity.ok(itemService.deleteById(id));
    }

}
