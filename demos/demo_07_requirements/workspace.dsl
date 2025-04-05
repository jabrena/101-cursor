workspace "Greek Good Service" {
    model {
        user = person "User" "Final user which interact with the REST API"
        
        greekGodSystem = softwareSystem "Greek Good REST System based on Spring Boot framework" {
            description "bla bla bla"
            
            springboot = container "greek-good-service" {
                technology "Azure App Service"
                tags "Azure - App Service"
                
                restController = component "Gods Rest Controller" "Provide information about Greek gods in JSON format"
                service = component "Greek God Service" "Interact with the Database Repository to provide the Greek gods"
                schedulerService = component "My JSON Server Synchronizer Service" "This service interact with the third party service and store the information in the internal database. The process is executed every 10 seconds"
                repository = component "Grek God Repository" "Spring Boot Data ListCRUD Repository to get access to a PostgreSQL database"
            }
            
            database = container "Azure Database for PostgreSQL" {
                description "Managed PostgreSQL database service"
                tags "Azure - PostgreSQL Server"
            }
        }

        externalSystem = softwareSystem "My JSON Server" "Provide a REST endpoint to retrieve Greek God data" {
            tags external
            description "GET https://my-json-server.typicode.com/jabrena/latency-problems/greek"
            url "https://my-json-server.typicode.com/jabrena/latency-problems/greek"
        }
        
        azure = deploymentEnvironment "Azure" {
            appGateway = deploymentNode "Azure Application Gateway" {
                description "Provides load balancing, SSL termination and routing"
                tags "Azure - Application Gateway"
            }

            vnet = deploymentNode "Virtual Network" {
                description "Azure Virtual Network for service isolation"
                tags "Azure - Virtual Network"

                appService = deploymentNode "App Service Plan" {
                    webapp = deploymentNode "Web App" {
                        containerInstance springboot
                    }
                }

                dbServer = deploymentNode "Azure Database for PostgreSQL" {
                    containerInstance database
                }
            }
        }
        
        # Relationships
        user -> greekGodSystem "Accesses via HTTPS"
        greekGodSystem -> externalSystem "Gets greek gods data [HTTPS]"
        
        # Component relationships
        restController -> service "Greek Gods"
        service -> repository "Greek Gods"
        schedulerService -> repository "Write to the database"
        repository -> database "Read/Write [TCP]"
        schedulerService -> externalSystem "Gets greek gods data [HTTPS]"
        
        # Deployment relationships
        appGateway -> webapp "Routes requests to"
    }
    
    views {
        systemContext greekGodSystem {
            title "Azure System Context"
            include *
            autoLayout
        }
        
        container greekGodSystem {
            title "Azure Container View"
            include *
            autoLayout
        }
        
        component springboot {
            title "Spring Boot Components"
            include *
            autoLayout
        }
        
        deployment greekGodSystem "Azure" {
            include *
            autoLayout
        }
        
        theme default
        
        styles {
            element "Person" {
                background #1e6a9e
                color #ffffff
                shape Person
            }
            element "Software System" {
                background #999999
            }
            element "Container" {
                background #85c1e9
            }
            element "Database" {
                shape Cylinder
            }
            element "External System" {
                background #999999
            }
            element "Azure - Application Gateway" {
                background #0078d4
                color #ffffff
                shape RoundedBox
                strokeWidth 2
                fontSize 14
            }
        }
    }
} 