package warehouse_api.model;

import warehouse_api.model.dto.CustomerCreateDto;
import warehouse_api.model.dto.DetailsCreateDto;
import warehouse_api.model.dto.DetailsDto;
import warehouse_api.model.enums.CustomerType;
import warehouse_api.model.enums.DetailsType;

import java.util.ArrayList;
import java.util.List;

public class StubGenerator {

    public static DetailsCreateDto getDetailsCreateDto() {
        List<DetailsDto> details = new ArrayList<DetailsDto>() {{
            add(new DetailsDto("test name", 50D, "additional info"));
            add(new DetailsDto("test name", 60D, "additional info"));
        }};

        DetailsCreateDto createDto = new DetailsCreateDto(details, DetailsType.INCOME, "test name");

        return createDto;
    }

    public static CustomerCreateDto getCustomerCreateDto() {
        CustomerCreateDto createDto = new CustomerCreateDto();
        createDto.setCustomerName("TestCustomerName");
        createDto.setCustomerType(CustomerType.PARTNER);
        createDto.setCustomerAddress("test address");

        return createDto;
    }
}
