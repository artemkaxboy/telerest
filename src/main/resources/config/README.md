# Config directory

## Usage

You can place here configuration yaml file that override options set in main application.yml.
At the same time these settings will not be stored to public repository due to .gitignore file settings.
You can also add this file as a volume to docker container to override properties there
if you for some reason do not want to use environment variables starting container.

## Example

`application.yml`:

    telegram:
      default-chat-id: 312345658
      token: 612345674:AAGWTqvadegaxKojlCiemYZ_DREbae4gPRa
