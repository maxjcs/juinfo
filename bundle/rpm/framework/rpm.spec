Summary: Alibaba @appName@
Name: alibaba-cn-@appName@
Version: 1.0
Release: @date@
Group: china/@appName@
License: Proprietary
Prefix: @prefixDir@
BuildArch: noarch

%description
%prep
%build
%install
%preun
%postun

%pre
rm -rf $RPM_INSTALL_PREFIX/target

%post
chmod 755 $RPM_INSTALL_PREFIX/target/rpm/install.sh

%files
%verify(md5 size mtime) @fileList@
