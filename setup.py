#!/usr/bin/env python

"""The setup script."""

from setuptools import setup, find_packages

with open('README.md') as readme_file:
    readme = readme_file.read()

requirements = ['Click>=7.0', 'flask>=1.1.2', 'docker>=4.3.1']

setup_requirements = ['pytest-runner', ]

test_requirements = ['pytest>=3', ]

setup(
    author="Alexander Bigerl",
    author_email='bigerl@mail.upb.de',
    python_requires='>=3.5',
    classifiers=[
        'Development Status :: 2 - Pre-Alpha',
        'Intended Audience :: Developers',
        'Natural Language :: English',
        'Programming Language :: Python :: 3',
        'Programming Language :: Python :: 3.5',
        'Programming Language :: Python :: 3.6',
        'Programming Language :: Python :: 3.7',
        'Programming Language :: Python :: 3.8',
    ],
    description="Continous benchmarking tool for RDF triple stores.",
    entry_points={
        'console_scripts': [
            'basilisk=basilisk.cli:main',
        ],
    },
    install_requires=requirements,
    long_description=readme + '\n\n',
    include_package_data=True,
    keywords='basilisk',
    name='basilisk',
    packages=find_packages(include=['basilisk', 'basilisk.*']),
    setup_requires=setup_requirements,
    test_suite='tests',
    tests_require=test_requirements,
    url='https://github.com/bigerl/basilisk',
    version='0.1.0',
    zip_safe=False,
)
