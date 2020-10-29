"""Console script for basilisk."""
import click

import basilisk


@click.command()
def main(args=None):
    """Console script for basilisk."""
    click.echo("Replace this message by putting your code into "
               "basilisk.cli.main")
    click.echo("See click documentation at https://click.palletsprojects.com/")
    return 0


if __name__ == "__main__":
    basilisk.app.run(host='0.0.0.0', port='8000', debug=True)
