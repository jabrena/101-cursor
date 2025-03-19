workspace {
    model {
        user = person "User"
        
        greekGodSystem = softwareSystem "System" {
            restController = container "Spring Boot Rest Controller" "Allows users to retrieve Greek god information"
            service = container "Spring Boot Service" "Provides Greek God data"
            schedulerService = container "Spring Boot Service2" "Periodical Greek God Database update"
            repository = container "Spring Data Repository" "Database access"
            database = container "Database" "PostgreSQL" "Relational Database"
        }
        
        externalSystem = softwareSystem "My JSON Server" "External system"
        
        user -> greekGodSystem
        restController -> service
        service -> repository "Read"
        schedulerService -> repository "Write"
        repository -> database "Read/Write [TCP]"
        schedulerService -> externalSystem "Gets greek gods from [HTTPS]"
    }
    
    views {
        systemContext greekGodSystem {
            include *
            autoLayout
        }
        
        container greekGodSystem {
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
        }
    }
} 