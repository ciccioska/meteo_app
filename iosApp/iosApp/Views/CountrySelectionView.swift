import SwiftUI
import shared

struct CountrySelectionView: View {
    @Binding var selectedCountry: Country
    
    @ObservedObject private(set) var viewModel: ViewModel
    @Environment(\.presentationMode) var presentationMode: Binding<PresentationMode>
    var body: some View {
        NavigationView {
            listView()
            .navigationBarTitle("Select Country")
        }
    }

    private func listView() -> AnyView {
        switch viewModel.countries {
        case .loading:
            return AnyView(Text("Loading...").multilineTextAlignment(.center))
        case .result(let countries):
            return AnyView(List(countries) { country in
                GenericRow(value: country.name).onTapGesture {
                        selectedCountry = country
                        presentationMode.wrappedValue.dismiss()
                    }
                }
            )
        case .error(let description):
            return AnyView(Text(description).multilineTextAlignment(.center))
        }
    }
}

extension CountrySelectionView {
    
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
                    self.countries = .error(error?.localizedDescription ?? "An error occurred while loafding countries")
                }
            })
        }
    }
}

extension Country: Identifiable { }
