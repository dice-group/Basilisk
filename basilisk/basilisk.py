"""Main module."""
from flask import Flask, escape, request

app = Flask(__name__)


@app.route('/')
def hello():
    name = request.args.get("name", "World")
    return f'Hello, {escape(name)}!'


@app.route('/triplestores')
def get_triplestores():
    return "TODO"


@app.route('/benchmarks')
def get_benchmarks():
    return "TODO"


@app.route('/results')
def get_results():
    """ execute sparql query"""
    return "TODO"
