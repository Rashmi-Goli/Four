package com.rg.four;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.core.importer.ImportOption;
import org.junit.jupiter.api.Test;

class ArchTest {

    @Test
    void servicesAndRepositoriesShouldNotDependOnWebLayer() {
        JavaClasses importedClasses = new ClassFileImporter()
            .withImportOption(ImportOption.Predefined.DO_NOT_INCLUDE_TESTS)
            .importPackages("com.rg.four");

        noClasses()
            .that()
            .resideInAnyPackage("com.rg.four.service..")
            .or()
            .resideInAnyPackage("com.rg.four.repository..")
            .should()
            .dependOnClassesThat()
            .resideInAnyPackage("..com.rg.four.web..")
            .because("Services and repositories should not depend on web layer")
            .check(importedClasses);
    }
}
