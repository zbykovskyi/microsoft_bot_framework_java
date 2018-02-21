# Simple client for Microsoft BotFramework on Java

This is SpringBoot based REST application with two-directions authentication (BotConnector->BotApp and BotApp->BotConnector) and simple AdaptiveCard flow. (https://docs.microsoft.com/en-us/bot-framework/rest-api/bot-framework-rest-connector-authentication)

Usage
-----
To run this application you need to cnange application.yml file and fill up bot application credentials
* azure:
    * botframework.client:
        * id : <app_id>
        * secret : <app_secret>

After application starts - set http://localhost:8080/callback at MicrosoftBot Emulator to test.
