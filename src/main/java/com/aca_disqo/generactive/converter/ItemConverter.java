package com.aca_disqo.generactive.converter;


import com.aca_disqo.generactive.controller.dto.ItemDTO;
import com.aca_disqo.generactive.repository.model.Item;

import java.util.List;

public interface ItemConverter {

    List<ItemDTO> bulkConvert(List<Item> items);

    ItemDTO convert(Item item);

    ItemDTO convertFromCSV(String[] csvLine);

}
