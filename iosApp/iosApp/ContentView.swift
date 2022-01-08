import SwiftUI
import shared

struct ContentView: View {
  @ObservedObject private(set) var viewModel: ViewModel

    var body: some View {
        NavigationView {
            listView()
            .navigationBarTitle("Select Country")
            .navigationBarItems(trailing:
                Button("Reload") {
                    self.viewModel.loadCountries()
            })
        }
    }

    private func listView() -> AnyView {
        switch viewModel.countries {
        case .loading:
            return AnyView(Text("Loading...").multilineTextAlignment(.center))
        case .result(let countries):
            return AnyView(List(countries) { country in
                CountryRow(country: country)
            })
        case .error(let description):
            return AnyView(Text(description).multilineTextAlignment(.center))
        }
    }
}

extension ContentView {
    
    enum LoadableCountry {
        case loading
        case result([Country])
        case error(String)
    }

    class ViewModel: ObservableObject {
        let sdk: AirVisualSDK
        @Published var countries = LoadableCountry.loading

        init(sdk: AirVisualSDK) {
            self.sdk = sdk
            self.loadCountries()
        }

        func loadCountries() {
            self.countries = .loading
            sdk.getAllCountries(completionHandler: { countries, error in
                if let countries = countries {
                    self.countries = .result(countries)
                } else {
                    self.countries = .error(error?.localizedDescription ?? "error")
                }
            })
        }
    }
}

extension Country: Identifiable { }
