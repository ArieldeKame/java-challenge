package jp.co.axa.apidemo.controllers;

import jp.co.axa.apidemo.entities.Employee;
import jp.co.axa.apidemo.repositories.EmployeeRepository;
import jp.co.axa.apidemo.services.EmployeeServiceImpl;
import org.json.JSONArray;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@WebMvcTest(value=EmployeeController.class)
public class EmployeeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private EmployeeServiceImpl employeeService;

    @MockBean
    private EmployeeRepository employeeRepo;

    @Test
    public void getEmployee() throws Exception {
        Employee mockEmployee = new Employee(1L, "ariel", "engineering", 10000000);

        Mockito.when(employeeService.getEmployee(Mockito.anyLong())).thenReturn(mockEmployee);
        employeeRepo.save(mockEmployee);

        RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
                "/api/v1/employees/1").accept(
                MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        System.out.println(result.getResponse());
        String expected = "{\"id\":1,\"name\":\"ariel\",\"department\":\"engineering\",\"salary\":10000000}";

        JSONAssert.assertEquals(expected, result.getResponse()
                .getContentAsString(), false);

        assertEquals(HttpStatus.OK.value(), result.getResponse().getStatus());
    }

    @Test
    public void deleteEmployee() throws Exception {
        this.addEmployee();

        RequestBuilder requestBuilder = MockMvcRequestBuilders.delete(
                "/api/v1/employees/1").accept(
                MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        assertEquals(employeeRepo.findAll().size(), 0L);

        assertEquals(HttpStatus.OK.value(), result.getResponse().getStatus());
    }

    @Test
    public void addEmployee() throws Exception {
        Employee mockEmployee = new Employee(1L, "ariel", "engineering", 10000000);
        String mockEmployeeJson = "{\"id\":1,\"name\":\"ariel\",\"department\":\"engineering\",\"salary\":10000000}";

        Mockito.when(employeeService.addEmployee(Mockito.any(Employee.class))).thenReturn(mockEmployee);

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/api/v1/employees")
                .accept(MediaType.APPLICATION_JSON).content(mockEmployeeJson)
                .contentType(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        MockHttpServletResponse response = result.getResponse();

        assertEquals(HttpStatus.OK.value(), response.getStatus());

        this.getEmployee();
    }

    @Test
    public void updateEmployee() throws Exception {
        Employee mockEmployee = new Employee(1L, "arielle", "sales", 15000000);
        String updateMockEmployeeJson = "{\"id\":1,\"name\":\"arielle\",\"department\":\"engineering\",\"salary\":10000000}";

        Mockito.when(employeeService.addEmployee(Mockito.any(Employee.class))).thenReturn(mockEmployee);
        employeeRepo.save(mockEmployee);

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .put("/api/v1/employees/1")
                .accept(MediaType.APPLICATION_JSON).content(updateMockEmployeeJson)
                .contentType(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        MockHttpServletResponse response = result.getResponse();

        assertEquals(HttpStatus.OK.value(), response.getStatus());

        this.getEmployee();
    }
}