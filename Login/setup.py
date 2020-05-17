from setuptools import find_packages
from setuptools import setup

setup(
    name="Server",
    version="1.0.0",
    url="https://lidar.icedcoffee.dev",
    license="MIT",
    maintainer="Daniel Jones",
    maintainer_email="hello@danjones.dev",
    description="Login server for LiDAR",
    long_description="Login server for LiDAR",
    packages=find_packages(),
    include_package_data=True,
    zip_safe=False,
    install_requires=["flask"],
    extra_require={},
)
