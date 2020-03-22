package com.study;

import com.thoughtworks.xstream.XStream;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class OrganizationLoader {
    private OrganizationLoader() {
    }

    public static Organization loader(File orgDirectory) {
        if (orgDirectory == null || !orgDirectory.exists() || !orgDirectory.isDirectory()) {
            return null;
        }

        final File[] files = orgDirectory.listFiles();
        if (files == null || files.length < 1) {
            return null;
        }

        XStream xStream = new XStream();
        xStream.processAnnotations(Department.class);

        Organization organization = new Organization();

        List<Department> departments = new ArrayList<>();
        for (File file : files) {
            try {
                String xml = FileUtils.readFileToString(file, "UTF-8");
                Department department = (Department) xStream.fromXML(xml);
                departments.add(department);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        organization.setDepartments(departments);

        return organization;
    }
}


