dataSource {
	pooled = true
	url = "jdbc:mysql://localhost/stackoverflowdb"
	driverClassName = "com.mysql.jdbc.Driver"
	username = "root"
	password = "root"
}
hibernate {
    cache.use_second_level_cache = true
    cache.use_query_cache = false
//    cache.region.factory_class = 'net.sf.ehcache.hibernate.EhCacheRegionFactory' // Hibernate 3
    cache.region.factory_class = 'org.hibernate.cache.ehcache.EhCacheRegionFactory' // Hibernate 4
    singleSession = true // configure OSIV singleSession mode
    flush.mode = 'manual' // OSIV session flush mode outside of transactional context
}

// environment specific settings
environments {
    development {
        dataSource {
            dbCreate = "update" // one of 'create', 'create-drop', 'update', 'validate', ''
            url = "jdbc:mysql://localhost/stackoverflowdb?useUnicode=yes&characterEncoding=UTF-8"
			
		}
    }
    test {
        dataSource {
            dbCreate = "update"
            url = "jdbc:mysql://localhost/stackoverflowdb?useUnicode=yes&characterEncoding=UTF-8"
		}
    }
    production {
		dataSource {
			dbCreate = "update"
			driverClassName = "org.postgresql.Driver"
			dialect = org.hibernate.dialect.PostgreSQLDialect
	
			uri = new URI(System.env.DATABASE_URL?:"postgres://test:test@localhost/test")
	
			url = "jdbc:postgresql://"+uri.host+uri.path
			username = uri.userInfo.split(":")[0]
			password = uri.userInfo.split(":")[1]
		}
       
    }
}
