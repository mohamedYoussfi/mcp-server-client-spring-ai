package net.youssfi.ssemcpserver.tools;

import org.springframework.ai.tool.annotation.Tool;
import org.springframework.context.annotation.Description;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class StockExchangeService {
    private List<Company> companies = List.of(
            new Company("Maroc Telecom","Telecom",3.6,10600,"Maroc"),
            new Company("OCP","Extraction minière",5.6,20000,"Maroc")
    );
    @Tool(description = "Get a company by name")
    public Company getCompanyByName(String companyName) {
        return companies.stream().filter(c->c.name().equals(companyName)).findFirst()
                .orElseThrow(()->new RuntimeException(String.format("Company %s not found", companyName)));
    }
    @Tool(description = "Get All Companies")
    public List<Company> getAllCompanies() {
        return companies;
    }
    @Tool
    public Stock getStockByCompany(String companyName) {
        return new Stock(companyName, LocalDate.now(), 100+Math.random()*1000);
    }
}

record Company(
        String name,
        String activity,
        @Description("The turnover in Milliard $")
        double turnover,
        int employeesNumber, String country){}
record Stock(String companyName, LocalDate date, double stock){ }
