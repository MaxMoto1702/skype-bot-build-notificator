var bak = {
    "type": "message/card",
    "attachments": [
        {
            "contentType": "application/vnd.microsoft.card.hero",
            "content": {
                "title": "Сейчас будет установка на DEV стенде",
                "subtitle": "Для приостановки на некоторое время, нажмите соотвествующую кнопку ниже",
                "buttons": [
                    {
                        "type": "imBack",
                        "title": "Пауза на 5 минут",
                        "value": "подожди 5 минут, пожалуйста"
                    },
                    {
                        "type": "imBack",
                        "title": "Пауза на 10 минут",
                        "value": "подожди 10 минут, пожалуйста"
                    },
                    {
                        "type": "imBack",
                        "title": "Пауза на 15 минут",
                        "value": "подожди 15 минут, пожалуйста"
                    }
                ]
            }
        }
    ]
};

var testModel = {
    "action": null,
    "attachmentLayout": null,
    "attachments": [{
        "class": "com.microsoft.bot.connector.model.Attachment",
        "content": {
            "buttons": [{
                "class": "com.microsoft.bot.connector.model.CardAction",
                "image": null,
                "title": "Пауза на 5 минут",
                "type": "imBack",
                "value": "подожди 5 минут, пожалуйста"
            }, {
                "class": "com.microsoft.bot.connector.model.CardAction",
                "image": null,
                "title": "Пауза на 10 минут",
                "type": "imBack",
                "value": "подожди 10 минут, пожалуйста"
            }, {
                "class": "com.microsoft.bot.connector.model.CardAction",
                "image": null,
                "title": "Пауза на 15 минут",
                "type": "imBack",
                "value": "подожди 15 минут, пожалуйста"
            }],
            "class": "com.microsoft.bot.connector.model.HeroCard",
            "images": [],
            "subtitle": "Для приостановки на некоторое время, нажмите соотвествующую кнопку ниже",
            "tap": null,
            "text": null,
            "title": "Test model"
        },
        "contentType": "application/vnd.microsoft.card.hero",
        "contentUrl": null,
        "name": null,
        "thumbnailUrl": null
    }],
    "channelData": null,
    "channelId": null,
    "class": "com.microsoft.bot.connector.model.Activity",
    "conversation": null,
    "entities": [],
    "from": null,
    "historyDisclosed": null,
    "id": null,
    "locale": null,
    "membersAdded": [],
    "membersRemoved": [],
    "recipient": null,
    "replyToId": null,
    "serviceUrl": null,
    "summary": null,
    "text": null,
    "textFormat": null,
    "timestamp": null,
    "topicName": null,
    "type": "message/card"
};

var r = {
    "attachmentLayout": "carousel",
    "attachments": [
        {
            "contentType": "application/vnd.microsoft.card.hero",
            "content": {
                "title": "I'm a hero card about Pig Latin",
                "subtitle": "PigLatin Wikipedia Page",
                "images": [
                    {
                        "url": "https://<ImageUrl1>"
                    }
                ],
                "buttons": [
                    {
                        "type": "openUrl",
                        "title": "WikiPedia Page",
                        "value": "https://en.wikipedia.org/wiki/{cardContent.Key}"
                    }
                ]
            }
        },
        {
            "contentType": "application/vnd.microsoft.card.hero",
            "content": {
                "title": "I'm a hero card about pork shoulder",
                "subtitle": "Pork Shoulder Wikipedia Page",
                "images": [
                    {
                        "url": "https://<ImageUrl2>"
                    }
                ],
                "buttons": [
                    {
                        "type": "openUrl",
                        "title": "WikiPedia Page",
                        "value": "https://en.wikipedia.org/wiki/{cardContent.Key}"
                    }
                ]
            }
        },
        {
            "contentType": "application/vnd.microsoft.card.hero",
            "content": {
                "title": "I'm a hero card about Bacon",
                "subtitle": "Bacon Wikipedia Page",
                "images": [
                    {
                        "url": "https://<ImageUrl3>"
                    }
                ],
                "buttons": [
                    {
                        "type": "openUrl",
                        "title": "WikiPedia Page",
                        "value": "https://en.wikipedia.org/wiki/{cardContent.Key}"
                    }
                ]
            }
        }
    ],
};