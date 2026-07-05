# KeyForge

Il progetto è stato configurato in modo da funzionare con il `DataSource`, di conseguenza è necessario configurare appositamente il file `WebContent/META-INF/context.xml` con le informazioni per la connessione al database (e.g., URI, username e password).

Segue un esempio di configurazione:

```xml
<Context>
	<Resource
		name="jdbc/keyforge"
		auth="Container"
		type="javax.sql.DataSource"
		driverClassName="com.mysql.cj.jdbc.Driver"
		url="jdbc:mysql://localhost:3306/keyforge"
		username="..."
		password="..." />
</Context>
```
