import SwiftUI
import shared

struct CitySelectionView: View {
    @Binding var selectedCity: City?
    
    @ObservedObject private(set) var viewModel: ViewModel
    @Environment(\.presentationMode) var presentationMode: Binding<PresentationMode>
    var body: some View {
        NavigationView {
            listView()
            .navigationBarTitle("Select city")
        }
    }

    private func listView() -> AnyView {
        switch viewModel.cities {
        case .loading:
            return AnyView(Text("Loading...").multilineTextAlignment(.center))
        case .result(let cities):
            return AnyView(List(cities) { city in
                GenericRow(value: city.name).onTapGesture {
                        selectedCity = city
                        presentationMode.wrappedValue.dismiss()
                    }
                }
            )
        case .error(let description):
            return AnyView(Text(description).multilineTextAlignment(.center))
        }
    }
}

extension CitySelectionView {
    
    enum LoadableCity {
        case loading
        case result([City])
        case error(String)
    }

    class ViewModel: ObservableObject {
        var currentCountry: Country
        var currentNationState: NationState
        let sdk: AirVisualSDK
        @Published var cities = LoadableCity.loading

        init(sdk: AirVisualSDK, country: Country, nationState: NationState) {
            self.sdk = sdk
            self.currentCountry = country
            self.currentNationState = nationState
            self.loadCities()
        }

        func loadCities() {
            self.cities = .loading
            sdk.getCities(country: self.currentCountry, nationState: self.currentNationState, completionHandler: { cities, error in
                if let cities = cities {
                    self.cities = .result(cities)
                } else {
                    self.cities = .error(error?.localizedDescription ?? "error")
                }
            })
        }
    }
}

extension City: Identifiable { }
