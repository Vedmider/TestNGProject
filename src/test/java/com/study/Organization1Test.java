package com.study;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.File;
import java.util.List;

public class Organization1Test {
    private Organization organization;

    @DataProvider
    public Object[][] dataEmployeeCount() {
        return new Object[][]{{"dep1", 2}};
    }

    @DataProvider
    public Object[][] dataEmployeeLastName() {
        return new Object[][]{{"dep2", 1, "empLastName2_1"}};
    }

    @BeforeMethod
    public void setUp() throws Exception {
        File fRoot = new File(".");
        File orgDir = new File(fRoot, "src/test/resources/org1");
        Assert.assertTrue(orgDir.exists());
        Assert.assertTrue(orgDir.isDirectory());

        organization = OrganizationLoader.loader(orgDir);
        Assert.assertNotNull(organization);
    }

    @Test
    public void testDepartmentCount() throws Exception {
        Assert.assertEquals(organization.getDepartments().size(), 3);
    }

    @Test(dependsOnMethods = {"testDepartmentCount"}, dataProvider = "dataEmployeeCount")
    public void testEmployeesCount(String depName, int countEmployee) throws Exception {
        final List<Department> departments = organization.getDepartments();
        int i = 0;
        Department department;
        do {
            department = departments.get(i++);
        } while (!department.getName().equals(depName));
        Assert.assertEquals(department.getName(), depName);
        Assert.assertEquals(department.getEmployees().size(), countEmployee);
    }

    @Test(dependsOnMethods = {"testDepartmentCount"}, dataProvider = "dataEmployeeLastName")
    public void testEmployeeLastName(String depName, int employeeIndex, String lastName) throws Exception {
        final List<Department> departments = organization.getDepartments();
        int i = 0;
        Department department;
        do {
            department = departments.get(i++);
        } while (!department.getName().equals(depName));
        Assert.assertEquals(department.getName(), depName);
        Employee employee = department.getEmployees().get(employeeIndex);
        Assert.assertEquals(employee.getLastName(), lastName);
    }
}
