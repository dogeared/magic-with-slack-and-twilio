## SOLID Principles with Slack and Twilio

The purpose of this app is to deliver a random [Magic the Gathering](https://magic.wizards.com/en) playing card image.

This repo can be deployed to Heroku and integrated with both Slack and Twilio. That is, in your Slack organization,
you can setup a slash command that when entered will display a card image in the channel the command was issued. And,
if configured, sending the command: `magic` to your Twilio number will respond with the magic card image as an MMS
message.

Watch for the forthcoming blog post!

In the meantime, you can use the friendly Heroku button below to deploy app to your own Heroku instance. I will be 
updating this README in the near future with specific instructions to configure both Slack and Twilio.

[![Deploy](https://www.herokucdn.com/deploy/button.svg)](https://heroku.com/deploy)