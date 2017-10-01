## SOLID Principles with Slack and Twilio

The purpose of this app is to deliver a random [Magic the Gathering](https://magic.wizards.com/en) playing card image.

This repo can be deployed to Heroku and integrated with both Slack and Twilio. That is, in your Slack organization,
you can setup a slash command that when entered will display a card image in the channel the command was issued. And,
if configured, sending the command: `magic` to your Twilio number will respond with the magic card image as an MMS
message.

Watch for the forthcoming blog post!

In the meantime, you can follow the instructions below to get the app set up.

### Deploy to Heroku

The easiest way to deploy the app to Heroku is to use the friendly purple button:

[![Deploy](https://www.herokucdn.com/deploy/button.svg)](https://heroku.com/deploy)

You’ll need to provide two details: a `BASE_URL` and a `SLACK_TOKENS`.

The `BASE_URL` is the fully qualified name of your heroku app. For instance, I have the app deployed to: 
`https://random-magic-card.herokuapp.com`. You simply follow the same format based on the `app name` you enter: 
`https://<app name>.herokuapp.com`

The `SLACK_TOKENS` is populated with values from the `Verification Token` in the Slack app definition (see below). If you are 
configuring the app for a single Slack Workspace, then you’ll have a single token. If (like me) you have multiple 
Workspaces and want the app to work in all of them, then you can enter a comma-separated list of Slack tokens.

![slack token](../../raw/readme_assets/images/slack_token.png)

**Note:** you can put in a dummy placeholder for the `SLACK_TOKENS` for now and update it later when when you set up 
your Slack app.

### Set up Slack

Navigate to [https://api.slack.com/apps](https://api.slack.com/apps) and click the `Create New App` button to get 
started:

![create slack app](../../raw/readme_assets/images/create_slack_app.png)

Enter in values for the `App Name` and choose the `Workspace` you’ll be adding the app to:

![create slack app popup](../../raw/readme_assets/images/create_slack_app_popup.png)

Next, click the `Slash Commands` link on the left side and click the `Create New Command` button:

![create slack app popup](../../raw/readme_assets/images/slack_slash_command.png)

Fill in values for `Command` (like: `/magic`), `Request URL` (like: https://<your app name>.herokuapp.com/api/v1/slack), 
and a `Short Description`. Click the `Save` button.

![create slack app popup](../../raw/readme_assets/images/create_command.png)

At this point, you Slack slash command is all setup:

![create slack app popup](../../raw/readme_assets/images/slack_slash_command_created.png)

Click `Basic Information` on the left side and expand the `Install app to your workspace` section. Click the `Install 
app to Workspace` button.

![slack install app](../../raw/readme_assets/images/slack_install_app.png)

Click the `Authorize` button:

![create slack app popup](../../raw/readme_assets/images/slack_app_confirm.png)

Scroll down on the `Basic Information` screen you are returned to and make note of the `Verification Token`. 

![slack token](../../raw/readme_assets/images/slack_token.png)

NOTE: If you’ve installed the Heroku CLI, you can issue this command to set the `SLACK_TOKENS` property properly:

```
heroku config:set \
SLACK_TOKENS=<comma separated tokens> \
--app <your heroku app name>
```

You should now be able to issue the slash command in a channel in your Slack org:

![create slack app popup](../../raw/readme_assets/images/slack_slash_command_in_action.png)

### Set up Twilio

Navgiate to your Twilio [Console Dashboard](https://www.twilio.com/console)

![twilio console](../../raw/readme_assets/images/twilio_console.png)

Click the three dots and choose `Programmable SMS`:

![programmable sms](../../raw/readme_assets/images/programmable_sms_1.png)

Click `Messaging Services`:

![programmable sms](../../raw/readme_assets/images/programmable_sms_2.png)

Click the red plus (`+`) button:

![messaging services](../../raw/readme_assets/images/messaging_services_1.png)

Enter a `Friendly Name`, Choose: `Notifications, 2-Way` for the `Use Case` and click the `Create` button:

![messaging services](../../raw/readme_assets/images/messaging_services_2.png)

Check the `Process Inbound Messages` checkbox and enter in the `Request URL` of your Heroku app (e.g. 
`https://<your app name>.herokuapp.com/api/v1/twilio`):

![messaging services](../../raw/readme_assets/images/messaging_services_3.png)

Click the `Save` button.

Click the `Numbers` link on the left and make sure your Twilio number is added to the messaging service:

![twilio number](../../raw/readme_assets/images/twilio_numbers.png)

**Note:** I am not publishing *my* Twilio number as I don't want you all jacking up my SMS charges. Maybe Twilio will
sponsor this app? If so, I'll publish the number! 

At this point, you should be able to test out the Twilio service by sending the word `magic` as a text message to your
Twilio number:

![twilio command](../../raw/readme_assets/images/twilio_command.png)

**Note:** If you send anything other than the word `magic` (case insensitive), you'll get the error response shown
above.





