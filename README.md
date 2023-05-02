#Architettura Oauth2 con spring

Esempio di una architettura basata su oauth2 scritta interamente in spring.
Essa si compone di un:
	
	
	-authentication server, che emette token firmati e autenticato con
	spring security

	-Resource-server, che è l'applicativo che esponde le risorse
	e si occupa di autenticatare e validare l'access token inviato
	dai vari client. La validazione l'ho fatta agganciando
	l'endpoint di introspezione esposto da oauth2, mediante
	una chiamata BASIC

	-Client: è l'applicativo che si registra con l'autentication
	server e chiama il resource-server inviando ad esso un token
	di accesso, dopo aver ovviamente eseguito l'autenticazione
	con oauth2 e aver recuperato il codice di autorizzazione


Al momento, ho configurato un solo UTENTE in memoria, con username "fabio" e password 
"12345", e l'unica autorizzazione è "libri.read". Per semplicità ho fatto si che 
il resources server profili l'utente in base ai ruoli e username regustrati 
sull'autentication server. 

NOTE
Per evitare problemi con i cookie, associare all'ip locale un alias modificando il file host. Nel mio
caso si chiama "auth-server".

Far partire in ordine:
	-Auth-server
	-Resource-server
	-Client

e agganciarsi alla porta 8081. Verrai reindirizzato su una pagina che ti metterà a disposizione due link, il primo è semplicemente
per testare il recupero del codice di autorizzazione dall'authentication-server. 
